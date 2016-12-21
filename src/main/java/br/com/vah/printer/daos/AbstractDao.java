package br.com.vah.printer.daos;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * Created by Jairoportela on 21/12/2016.
 */
public abstract class AbstractDao implements Serializable {

  @PersistenceContext
  private EntityManager em;

  public Session getSession() {
    return em.unwrap(Session.class);
  }

}
