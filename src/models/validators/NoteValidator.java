package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Note;

public class NoteValidator {

    public static List<String> validate(Note n){
        List<String> errors = new ArrayList<String>();

        String title_error = validateTitle(n.getTitle());
        if(!title_error.equals("")){
            errors.add(title_error);
        }

        String content_error = validateContent(n.getContent());
        if(!content_error.equals("")){
            errors.add(content_error);
        }
        return errors;
    }

    private static String validateTitle(String title){
        if(title == null || title.equals("")){
            return "タイトルを入力してください。";
        }
        return "";
    }

    private static String validateContent(String content){
        if(content == null || content.equals("")){
            return "ノートを入力してください。";
        }
        return "";
    }
}
