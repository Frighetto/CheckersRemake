<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt">
    <head>
        <title>Play Damas</title>
        <meta charset="UTF-8">
        <meta name="application-name" content="Play Damas">
        <meta name="description" content="Checkers game with real players and bots">
        <meta name="keywords" content="Players, Checkers">
        <meta name="author" content="Lucas Fernando Frighetto">
        <meta name="viewport" content="width=device-width, initial-scale=0.5">
        <link rel="icon" href="icon.png">     
    </head>    
    <body>       
        <script>
            setTimeout(() => {
                alert("<%= request.getAttribute("alert") %>");
                window.location = "/CHECKERS2/";
            }, 50);                     
        </script> 
    </body>
</html>