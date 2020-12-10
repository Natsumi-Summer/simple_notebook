<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<label for="title">タイトル</label><br/>
<input type="text" name="title" value="${note.title}"/>
<br/><br/>

<label for="content">ノート</label><br/>
<input type="text" name="content" value="${note.content}"/>
<br/><br/>

<input type="hidden" name="_token" value="${_token}"/>
<button type="submit">登録</button>