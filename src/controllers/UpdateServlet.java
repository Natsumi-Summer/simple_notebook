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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Note n = em.find(Note.class, (Integer)(request.getSession().getAttribute("note_id")));

            String title = request.getParameter("title");
            n.setTitle(title);

            String content= request.getParameter("content");
            n.setContent(content);

            Timestamp currentTIme = new Timestamp(System.currentTimeMillis());
            n.setUpdated_at(currentTIme);

            //バリデーションを実行してエラーがあったら編集画面のフォームに戻る
            List<String> errors = NoteValidator.validate(n);
            if(errors.size() > 0){
                em.close();

                //フォームに初期値を設定、さらにエラーメッセージを送る
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("note", n);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/notes/edit.jsp");
                rd.forward(request, response);
            }else{

            em.getTransaction().begin();
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "更新が完了しました。");
            em.close();

            request.getSession().removeAttribute("note_id");

            response.sendRedirect(request.getContextPath() + "/index");
            }
        }
    }

}
