package edu.sun.richfaces.manage;

import edu.sun.richfaces.beans.UserInformation;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: sunqipeng
 * Date: 12-7-25
 */
@ManagedBean
@ViewScoped
public class MainBean implements Serializable {

    public List<String> values = new ArrayList<String>();

    public String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<UserInformation> userInformations = new ArrayList<UserInformation>();

    public List<UserInformation> getUserInformations() {
        return userInformations;
    }

    public void setUserInformations(List<UserInformation> userInformations) {
        this.userInformations = userInformations;
    }

    private String testName;

    public String getTestName() {
        return new Date().toString() + testName;
    }

    @PostConstruct
    public void post() {
        userInformations = EntityManagerUtils.getEntityManager().createQuery("select u from UserInformation u ").getResultList();
    }

    public void helloAjax() {

        //userInformations.add(new UserInformation());
        EntityManager entityManager = EntityManagerUtils.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new UserInformation());
        entityManager.getTransaction().commit();
        entityManager.createQuery("select u from UserInformation u ").getResultList();

        System.out.println("this the message invoked");
        testName = new Date().toString();

    }


    public void save() {
        message = "data are saved , there are " + userInformations.size() + " data currently";
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
