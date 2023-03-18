package com.example.crudmysql.core;

import com.example.crudmysql.model.Jugador;
import com.example.crudmysql.model.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControllerJugador {
    public int guardarJugador(Jugador j) throws SQLException {
        int idJugadorGenrado = -1;
        int idPersonaGenerado = -1;
        String sql = "CALL guardarJugador(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        ConexionMySQL connMySQL = new ConexionMySQL();

        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.open();

        //Con este objeto invocaremos al StoredProcedure:
        CallableStatement cstmt = conn.prepareCall(sql);

        cstmt.setString(1, j.getPersona().getNombre());
        cstmt.setString(2, j.getPersona().getPrimerApellido());
        cstmt.setString(3, j.getPersona().getSegundoApellido());
        cstmt.setInt(4, j.getPersona().getEdad());
        cstmt.setString(5, j.getPersona().getTelefono());
        cstmt.setString(6, j.getPersona().getRfc());
        cstmt.setString(7, j.getEquipo());
        cstmt.setString(8, j.getPosicion());
        cstmt.registerOutParameter(9, Types.INTEGER);
        cstmt.registerOutParameter(10, Types.INTEGER);

        cstmt.executeQuery();

        idJugadorGenrado = cstmt.getInt(9);
        idPersonaGenerado = cstmt.getInt(10);
        j.setIdJugador(idJugadorGenrado);
        j.getPersona().setIdPersona(idPersonaGenerado);

        cstmt.close();
        connMySQL.close();

        return idJugadorGenrado;
    }

    public List<Jugador> getAll() throws Exception {
        //La consulta SQL a ejecutar:
        String sql = "SELECT * FROM v_jugador";

        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();

        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.open();

        //Con este objeto ejecutaremos la consulta:
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //Aquí guardaremos los resultados de la consulta:
        ResultSet rs = pstmt.executeQuery();

        List<Jugador> jugadores = new ArrayList<>();

        while (rs.next()) {
            jugadores.add(fill(rs));
        }

        rs.close();
        pstmt.close();
        connMySQL.close();

        return jugadores;
    }

    private Jugador fill(ResultSet rs) throws SQLException {
        Jugador j = new Jugador();
        Persona p = new Persona();

        p.setIdPersona(rs.getInt("persona_id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrimerApellido(rs.getString("primer_apellido"));
        p.setSegundoApellido(rs.getString("segundo_apellido"));
        p.setEdad(rs.getInt("edad"));
        p.setTelefono(rs.getString("telefono"));
        p.setRfc(rs.getString("rfc"));
        j.setIdJugador(rs.getInt("jugador_id"));
        j.setEquipo(rs.getString("equipo"));
        j.setPosicion(rs.getString("posicion"));
        j.setPersona(p);

        return j;
    }

    public int modificarJugador(Jugador j) throws SQLException {
        String sql = "CALL modificarJugador(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        ConexionMySQL connMySQL = new ConexionMySQL();

        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.open();

        //Con este objeto invocaremos al StoredProcedure:
        CallableStatement cstmt = conn.prepareCall(sql);

        cstmt.setInt(1, j.getIdJugador());
        cstmt.setString(2, j.getPersona().getNombre());
        cstmt.setString(3, j.getPersona().getPrimerApellido());
        cstmt.setString(4, j.getPersona().getSegundoApellido());
        cstmt.setInt(5, j.getPersona().getEdad());
        cstmt.setString(6, j.getPersona().getTelefono());
        cstmt.setString(7, j.getPersona().getRfc());
        cstmt.setString(8, j.getEquipo());
        cstmt.setString(9, j.getPosicion());

        cstmt.executeQuery();

        cstmt.close();
        connMySQL.close();

        return 1;
    }

    public void eliminarJugador(int idJ, int idP) throws SQLException {
        String sql = "CALL borrarJugador(?,?);";

        ConexionMySQL connMySQL = new ConexionMySQL();

        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.open();

        //Con este objeto invocaremos al StoredProcedure:
        CallableStatement cstmt = conn.prepareCall(sql);

        cstmt.setInt(1, idJ);
        cstmt.setInt(2, idP);

        cstmt.executeQuery();

        cstmt.close();
        connMySQL.close();
    }
}
