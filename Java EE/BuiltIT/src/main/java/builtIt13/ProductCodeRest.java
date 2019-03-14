/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builtIt13;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author c0719943
 */
@Path("productCode")
@ApplicationScoped
public class ProductCodeRest {
    
    @PersistenceContext(unitName = "builtIt13PU")
    private EntityManager em;
    
    // http://localhost:8080/BuiltIT/api/productCode
    @GET
    @Produces({"application/json"})
    public List<ProductCode> getAll() {
        List<ProductCode> productCodes = em.createQuery("SELECT p FROM ProductCode p").getResultList();
        return productCodes;
    }
    
    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public List<ProductCode> getOne(@PathParam("id") String id) {
        Query q = em.createQuery("SELECT p FROM ProductCode p WHERE p.prodCode = :id");
        q.setParameter("id", id);
        List<ProductCode> productCodes = q.getResultList();
        return productCodes;
    }
    
    @Inject
    private UserTransaction transaction;
        
    @POST
    @Consumes("application/json")
    public void addOne(ProductCode productCode) {
        try {
            transaction.begin();
            em.persist(productCode);
            transaction.commit();
        } catch (Exception ex) {
            Logger.getLogger(ProductCodeRest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @PUT
    @Consumes("application/json")
    public void editOne(ProductCode productCode, @PathParam("id") String id) {
        try {
            Query q = em.createQuery("SELECT p FROM ProductCode p WHERE p.prodCode = :id");
            q.setParameter("id", id);
            ProductCode savedPC = (ProductCode) q.getResultList();
            savedPC.setDescription(productCode.getDescription());
            savedPC.setDiscountCode(productCode.getDiscountCode());
            transaction.begin();
            em.merge(savedPC);
            transaction.commit();
        } catch (Exception ex) {
            Logger.getLogger(ProductCodeRest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @DELETE
    @Path("{id}")
    public void deleteOne(@PathParam("id") String id) {
        try {
            transaction.begin();
            ProductCode found = em.find(ProductCode.class, id);
            em.remove(found);
            transaction.commit();
        } catch (Exception ex) {
            Logger.getLogger(ProductCodeRest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
