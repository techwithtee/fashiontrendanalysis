package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.model.Designer;
import com.wileyedge.fashiontrendanalysis.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ProductDaoImpl productDao;

    @BeforeEach
    public void setup() {
        // Any setup required before tests
    }

    @Test
    public void testGetAllProducts() {
        List<Product> expectedProducts = Arrays.asList(
                new Product(1L, "Shirt", 1L, 1L, "Cotton shirt"),
                new Product(2L, "Pants", 2L, 1L, "Jeans")
        );

        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedProducts);

        List<Product> returnedProducts = productDao.getAllProducts();

        assertEquals(expectedProducts, returnedProducts);
    }

    @Test
    public void testGetProductById() {
        Product expectedProduct = new Product(1L, "Shirt", 1L, 1L, "Cotton shirt");

        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(expectedProduct);

        Product returnedProduct = productDao.getProductById(1L);

        assertEquals(expectedProduct, returnedProduct);
    }

    @Test
    public void testAddProduct() {
        Product product = new Product(null, "Shirt", 1L, 1L, "Cotton shirt");

        when(jdbcTemplate.update(anyString(), eq(product.getProductName()), eq(product.getCategoryId()), eq(product.getDesignerId()), eq(product.getProductDescription()))).thenReturn(1);
        when(jdbcTemplate.queryForObject(anyString(), eq(Long.class))).thenReturn(1L);

        Long returnedId = productDao.addProduct(product);

        assertEquals(1L, returnedId);
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product(1L, "Shirt", 1L, 1L, "Updated Cotton shirt");

        when(jdbcTemplate.update(anyString(), eq(product.getProductName()), eq(product.getCategoryId()), eq(product.getDesignerId()), eq(product.getProductDescription()), eq(product.getProductId()))).thenReturn(1);

        boolean result = productDao.updateProduct(1L, product);

        assertTrue(result);
    }

    @Test
    public void testDeleteProduct() {
        when(jdbcTemplate.update(anyString(), anyLong())).thenReturn(1);

        boolean result = productDao.deleteProduct(1L);

        assertTrue(result);
    }

    @Test
    public void testGetProductsByDesigner() {
        List<Product> expectedProducts = Arrays.asList(
                new Product(1L, "Shirt", 1L, 1L, "Cotton shirt")
        );

        when(jdbcTemplate.query(anyString(), eq(new Object[]{1L}), any(RowMapper.class))).thenReturn(expectedProducts);

        List<Product> returnedProducts = productDao.getProductsByDesigner(1L);

        assertEquals(expectedProducts, returnedProducts);
    }

    @Test
    public void testGetProductsByCategory() {
        List<Product> expectedProducts = Arrays.asList(
                new Product(1L, "Shirt", 1L, 1L, "Cotton shirt")
        );

        when(jdbcTemplate.query(anyString(), eq(new Object[]{1L}), any(RowMapper.class))).thenReturn(expectedProducts);

        List<Product> returnedProducts = productDao.getProductsByCategory(1L);

        assertEquals(expectedProducts, returnedProducts);
    }

    @AfterEach
    public void cleanup() {
        reset(jdbcTemplate);
    }

    @Test
    public void testRetrieveNonExistingProduct() {
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(null);

        Product result = productDao.getProductById(999L);
        assertNull(result);
    }

    @Test
    public void testUpdateNonExistingProduct() {
        Product product = new Product(999L, "NonExistent", 1L, 1L, "Description");
        when(jdbcTemplate.update(anyString(), eq(product.getProductName()), eq(product.getCategoryId()), eq(product.getDesignerId()), eq(product.getProductDescription()), eq(product.getProductId()))).thenReturn(0);

        boolean result = productDao.updateProduct(999L, product);
        assertFalse(result);
    }

    @Test
    public void testDeleteNonExistingProduct() {
        when(jdbcTemplate.update(anyString(), eq(999L))).thenReturn(0);

        boolean result = productDao.deleteProduct(999L);
        assertFalse(result);
    }

    @Test
    public void testDatabaseConnectionError() {
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenThrow(new DataAccessException("DB Connection Error") {});

        assertThrows(DataAccessException.class, () -> productDao.getProductById(1L));
    }

    @Test
    public void testAddDuplicateProduct() {
        Product product = new Product(null, "DuplicateProduct", 1L, 1L, "Description");
        doThrow(new DuplicateKeyException("Duplicate entry 'DuplicateProduct'"))
                .when(jdbcTemplate)
                .update(anyString(), eq(product.getProductName()), eq(product.getCategoryId()), eq(product.getDesignerId()), eq(product.getProductDescription()));
        assertThrows(DuplicateKeyException.class, () -> productDao.addProduct(product));
    }
    @Test
    public void testAssociateDesignerWithProduct() {
        when(jdbcTemplate.update(anyString(), eq(1L), eq(1L))).thenReturn(1);
        productDao.associateDesignerWithProduct(1L, 1L);
        verify(jdbcTemplate, times(1)).update(anyString(), eq(1L), eq(1L));
    }


    @Test
    public void testDissociateDesignerFromProduct() {
        when(jdbcTemplate.update(anyString(), eq(1L), eq(1L))).thenReturn(1);
        productDao.dissociateDesignerFromProduct(1L, 1L);
        verify(jdbcTemplate, times(1)).update(anyString(), eq(1L), eq(1L));
    }


    @Test
    public void testGetDesignersForProduct() {
        List<Designer> expectedDesigners = Arrays.asList(
                new Designer(1L, "Designer1", "Location1", 5, 100),
                new Designer(2L, "Designer2", "Location2", 3, 80)
        );
        when(jdbcTemplate.query(
                eq("SELECT * FROM designers WHERE designer_id IN (SELECT designer_id FROM product_designer_association WHERE product_id=?)"),
                any(RowMapper.class),
                eq(1L)
        )).thenReturn(expectedDesigners);


            List<Designer> returnedDesigners = productDao.getDesignersForProduct(1L);

            assertEquals(expectedDesigners, returnedDesigners);
        }
    }
