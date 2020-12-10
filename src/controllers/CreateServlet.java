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
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Note n = new Note();

            String title = request.getParameter("title");
            n.setTitle(title);

            String content = request.getParameter("content");
            n.setContent(content);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            n.setCreated_at(currentTime);
            n.setUpdated_at(currentTime);

            em.getTransaction().begin();
            em.persist(n);
            em.getTransaction().commit();
            em.close();

            response.sendRedirect(request.getContextPath() + "/index");
            }
    }

}
