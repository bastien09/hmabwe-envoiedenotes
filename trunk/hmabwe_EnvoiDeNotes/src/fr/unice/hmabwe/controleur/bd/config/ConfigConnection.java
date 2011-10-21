package fr.unice.hmabwe.controleur.bd.config;

import java.sql.*;
import java.io.*;
import java.util.*;
import java.net.URL;
import javax.sql.rowset.CachedRowSet;

/**
 * ConfigConnection.java
 * Initialise une connexion � une base
 * en lisant un fichier de propri�t�s
 * @version 3 (avec m�thode pour rowset)
 */

public class ConfigConnection {

  private ConfigConnection() { }

  /**
   * Obtenir une connexion � partir des infos qui sont
   * dans un fichier de propri�t�s.
   * Le fichier doit contenir les propri�t�s driver, url,
   * utilisateur, mdp (mot de passe)
   * @param nomFichierProp nom du fichier de propri�t�s (� mettre
   * dans le m�me r�pertoire que cette classe).
   * @return une connexion � la base
   */
  public static Connection getConnection(String nomFichierProp)
      throws IOException, ClassNotFoundException, SQLException {
    Properties props = new Properties();
    URL urlFichierProp = ConfigConnection.class.getResource(nomFichierProp);
    if (urlFichierProp == null) {
      throw new IOException("Fichier " + nomFichierProp + " pas trouv� !");
    }
    BufferedInputStream bis = null;
    try {
      bis = new BufferedInputStream(urlFichierProp.openStream());
      props.load(bis);
      String driver = props.getProperty("driver");
      String url = props.getProperty("url");
      String utilisateur = props.getProperty("utilisateur");
      String mdp = props.getProperty("mdp");
      Class.forName(driver);
      return DriverManager.getConnection(url, utilisateur, mdp);
    }
    finally {
      if (bis != null) {
        bis.close();
      }
    }

  }

  /**
   * Obtenir une connexion � partir des infos qui sont
   * dans un fichier de propri�t�s, du nom d'utilisateur
   * et du mot de passe pass�s en param�tre
   * Le fichier doit contenir les propri�t�s driver, url.
   * @param nomFichierProp nom du fichier de propri�t�s.
   * @param utilisateur nom de l'utilisateur.
   * @param mdp mot de passe de l'utilisateur.
   * @return une connexion � la base.
   */
  public static Connection getConnection(String nomFichierProp,
                                         String utilisateur,
                                         String mdp)
      throws IOException, ClassNotFoundException, SQLException {
    Properties props = new Properties();
    URL urlFichierProp = ConfigConnection.class.getResource(nomFichierProp);
    if (urlFichierProp == null) {
      throw new IOException("Fichier " + nomFichierProp + " pas trouv� !");
    }
    BufferedInputStream bis = null;
    try {
      bis = new BufferedInputStream(urlFichierProp.openStream());
      props.load(bis);
      String driver = props.getProperty("driver");
      String url = props.getProperty("url");
      Class.forName(driver);
      return DriverManager.getConnection(url, utilisateur, mdp);
    }
    finally {
      if (bis != null) {
        bis.close();
      }
    }
  }

  /**
   * Met les propri�t�s d'un rowset pour se connecter � la base de donn�es.
   * @param rs le rowset � initialiser.
   * @param nomFichierProp le nom du fichier de propri�t�s qui contient
   * les valeurs des propri�t�s pour la connexion.
   */
  public static void setPropsRowSet(CachedRowSet rs, String nomFichierProp)
      throws SQLException, IOException {
    Properties props = new Properties();
    URL urlFichierProp = ConfigConnection.class.getResource(nomFichierProp);
    if (urlFichierProp == null) {
      throw new IOException("Fichier " + nomFichierProp + " pas trouv� !");
    }
    BufferedInputStream bis = null;
    try {
      bis = new BufferedInputStream(urlFichierProp.openStream());
      props.load(bis);
      rs.setUrl(props.getProperty("url"));
      rs.setUsername(props.getProperty("utilisateur"));
      rs.setPassword(props.getProperty("mdp"));
    }
    finally {
      if (bis != null) {
        bis.close();
      }
    }

  }

} // ConfigConnection
