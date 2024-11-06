
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blog with Search and Login - README</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        h1, h2 {
            color: #1a73e8;
        }
        .section {
            padding: 50px 0;
        }
        .container {
            max-width: 900px;
        }
        .feature-image {
            max-width: 100%;
            border-radius: 8px;
            margin-bottom: 20px;
        }
        .card-title {
            font-size: 1.5em;
            color: #333;
        }
        footer {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 15px 0;
        }
    </style>
</head>
<body>

    <nav class="blue darken-3">
        <div class="nav-wrapper">
            <a href="#" class="brand-logo center">Blog with Search and Login</a>
        </div>
    </nav>

    <div class="container">
        <section class="section">
            <h1>Project Overview</h1>
            <p>
                The <strong>Blog with Search and Login</strong> project is a web application built with <strong>Spring Boot</strong>. 
                It allows administrators to manage articles, with functionalities like creating, editing, and deleting articles. 
                The app is secured with login functionality and features a dynamic category-based sidebar and a search bar for users to filter articles.
            </p>
            <img src="images/blog-homepage.png" alt="Blog Homepage" class="feature-image">
        </section>

        <section class="section">
            <h2>Technologies Used</h2>
            <ul>
                <li><strong>Spring Boot</strong> - For creating the backend and managing the entire application lifecycle.</li>
                <li><strong>Spring Security</strong> - For securing the admin panel with authentication.</li>
                <li><strong>Thymeleaf</strong> - For dynamic HTML templating.</li>
                <li><strong>JPA (Java Persistence API)</strong> - For managing the persistence of articles in a MySQL database.</li>
                <li><strong>Materialize CSS</strong> - For the responsive and modern frontend design.</li>
                <li><strong>MySQL</strong> - For storing articles, user information, and categories.</li>
            </ul>
        </section>

        <section class="section">
            <h2>Features</h2>
            <h3>Login Page</h3>
            <p>
                The admin panel is protected by a login system that requires a valid username and password.
                Only authenticated users with the appropriate credentials can access the panel.
            </p>
            <img src="images/login-page.png" alt="Login Page" class="feature-image">

            <h3>Admin Panel - Article Management</h3>
            <p>
                Administrators can add, modify, and delete articles. Each article includes a title, content, image, and category.
                Articles are listed in the admin panel as cards, which allow quick management actions such as editing or deleting.
            </p>
            <img src="images/admin-panel.png" alt="Admin Panel" class="feature-image">

            <h3>Article Display</h3>
            <p>
                On the user-facing side, articles are displayed dynamically, organized by categories. Users can click on a category in the sidebar to filter articles.
            </p>
            <img src="images/articles-page.png" alt="Articles Page" class="feature-image">

            <h3>Search Functionality</h3>
            <p>
                The application includes a search form that allows users to search articles by keyword, which can match the article's title or content.
            </p>
            <img src="images/search-functionality.png" alt="Search Functionality" class="feature-image">
        </section>

        <section class="section">
            <h2>Database Structure</h2>
            <p>
                The application uses a MySQL database to store articles and categories. Articles are linked to categories, allowing users to filter articles by their respective category.
            </p>
            <img src="images/database-schema.png" alt="Database Schema" class="feature-image">
        </section>

        <footer>
            <p>&copy; 2024 Blog with Search and Login. All rights reserved.</p>
        </footer>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>
