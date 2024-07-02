package com.ohih.scheduler.config.typehandler;

import com.ohih.scheduler.scheduler.dto.EventStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;

public class EnumTypeHandler extends BaseTypeHandler<EventStatus> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, EventStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name());
    }

    @Override
    public EventStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String status = rs.getString(columnName);
        return status == null ? null : EventStatus.valueOf(status.toUpperCase());
    }

    @Override
    public EventStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String status = rs.getString(columnIndex);
        return status == null ? null : EventStatus.valueOf(status.toUpperCase());
    }

    @Override
    public EventStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String status = cs.getString(columnIndex);
        return status == null ? null : EventStatus.valueOf(status.toUpperCase());
    }
}
