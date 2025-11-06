package br.com.easyfinancas.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static String URL;
    private static String USER;
    private static String PASS;

    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver Oracle JDBC não encontrado no classpath.", e);
        }

        // 1) Lê o perfil via env var DB_PROFILE (fiap | local). Default: local
        String profile = System.getenv("DB_PROFILE");
        if (profile == null || profile.isBlank()) profile = "local";

        // 2) Escolhe o arquivo de propriedades
        String file = profile.equalsIgnoreCase("fiap")
                ? "config/db-fiap.properties"
                : "config/db-local.properties";

        Properties p = new Properties();
        try (FileInputStream in = new FileInputStream(Paths.get(file).toFile())) {
            p.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Falha ao ler config: " + file, e);
        }

        URL  = p.getProperty("url");
        USER = p.getProperty("user");
        PASS = p.getProperty("pass");

        // 3) Permitir override por variáveis de ambiente se quiser (opcional)
        URL  = System.getenv().getOrDefault("DB_URL",  URL);
        USER = System.getenv().getOrDefault("DB_USER", USER);
        PASS = System.getenv().getOrDefault("DB_PASS", PASS);

        System.out.println("[DB] perfil=" + profile + " | url=" + URL + " | user=" + USER);
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
