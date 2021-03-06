package controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Note;
import models.validators.NoteValidator;
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

            //バリデーションを実行してエラーがあったら新規登録のフォームに戻る
            List<String> errors = NoteValidator.validate(n);
            if(errors.size() >0){
                em.close();

                //フォームに初期値を設定、さらにエラーメッセージを送る
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("note", n);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/notes/new.jsp");
                rd.forward(request, response);
            }else {

            em.getTransaction().begin();
            em.persist(n);
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "登録が完了しました。");
            em.close();

            response.sendRedirect(request.getContextPath() + "/index");
            }

            }
    }

}
