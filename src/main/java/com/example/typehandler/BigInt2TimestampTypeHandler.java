package com.example.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;

public class BigInt2TimestampTypeHandler extends BaseTypeHandler<Timestamp> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Timestamp parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, parameter.getTime());
    }

    @Override
    public Timestamp getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return timestamp(rs.getLong(columnName));
    }

    @Override
    public Timestamp getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return timestamp(rs.getLong(columnIndex));
    }

    @Override
    public Timestamp getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return timestamp(cs.getLong(columnIndex));
    }

    private Timestamp timestamp(long time) {
        if (time==0) {
            return null;
        }
        return new Timestamp(time);
    }

}
