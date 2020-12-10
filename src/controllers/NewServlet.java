package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Note;
import utils.DBUtil;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        Note n = new Note();

        String title = "Shopping List";
        n.setTitle(title);

        String content = "Grocery Store";
        n.setContent(content);

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        n.setCreated_at(currentTime);
        n.setUpdated_at(currentTime);

        em.persist(n);
        em.getTransaction().commit();

        response.getWriter().append(Integer.valueOf(n.getId()).toString());

        em.close();

    }

}
