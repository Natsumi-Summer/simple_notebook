<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>Simple Notebook</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>Simple Notebook</h1>
            </div>
            <div id="content">
            ${param.content}
            </div>
            <div id="footer">
                by Natsumi Fujimaki
            </div>
        </div>
    </body>
</html>