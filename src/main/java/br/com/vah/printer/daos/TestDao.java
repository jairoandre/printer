package br.com.vah.printer.daos;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jairoportela on 21/12/2016.
 */
@Stateless
public class TestDao extends AbstractDao {

  public List<Map<String, Object>> list() {
    String sql =
        "SELECT CD_CONVENIO, NM_CONVENIO FROM DBAMV.CONVENIO C WHERE C.SN_ATIVO = 'S' AND NM_CONVENIO LIKE '%SA%'";

    Session session = getSession();
    SQLQuery query = session.createSQLQuery(sql);

    List<Object[]> result = (List<Object[]>) query.list();

    List<Map<String, Object>> list = new ArrayList<>();

    for (Object[] obj : result) {
      Map<String, Object> map = new HashMap<>();
      map.put("codigo", obj[0]);
      map.put("convenio", obj[1]);
      list.add(map);
    }

    return list;
  }
}
