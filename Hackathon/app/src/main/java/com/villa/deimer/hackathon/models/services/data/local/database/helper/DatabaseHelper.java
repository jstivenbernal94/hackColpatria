package com.villa.deimer.hackathon.models.services.data.local.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.villa.deimer.hackathon.R;
import com.villa.deimer.hackathon.models.entities.User;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "dbprod.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<User, Integer> userDao = null;
    private RuntimeExceptionDao<User, Integer> userRuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    /*Funcion que permite crear la base de datos cuando inicia la aplicacion
     * Usa como parametros;
     * @param sqLiteDatabase -> extension de la base de datos para sqlite
     * @param source -> variable para la conexion a los recursos de sqlite
     */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource source) {
        try {
            TableUtils.createTable(source, User.class);
        } catch (SQLException sqlEx) {
            Log.e(this.getDatabaseName(), "Error: " + sqlEx.getMessage());
            throw new RuntimeException(sqlEx);
        }
    }

    /*Funcion que permite actualizar la base de datos cuando sea necesario
     * Usa como parametros;
     * @param db -> extension de la base de datos
     * @param source -> variable para la conexion a la base de datos
     * @param oldVersion -> numero de version actual de la base de datos
     * @param newVersion -> numero de la nueva version de la base de datos
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource source, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(source, User.class, true);
            onCreate(database, source);
        } catch (SQLException sqlEx) {
            Log.e(this.getDatabaseName(), "Error: " + sqlEx.getMessage());
            Log.e(DatabaseHelper.class.getSimpleName(), "Imposible eliminar la base de datos", sqlEx);
        }
    }

    @Override
    public void close() {
        super.close();
        userDao = null;
        userRuntimeDao = null;
    }

    public Dao<User, Integer> getUserDao() throws SQLException {
        if(userDao == null) userDao = getDao(User.class);
        return userDao;
    }
    public RuntimeExceptionDao<User, Integer> getUserRuntimeDao() {
        if(userRuntimeDao == null) userRuntimeDao = getRuntimeExceptionDao(User.class);
        return userRuntimeDao;
    }
}
