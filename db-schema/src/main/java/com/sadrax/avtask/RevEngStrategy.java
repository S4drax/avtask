package com.sadrax.avtask;


import org.hibernate.cfg.reveng.DelegatingReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.ReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.TableIdentifier;

public class RevEngStrategy extends DelegatingReverseEngineeringStrategy {

  public RevEngStrategy(ReverseEngineeringStrategy delegate) {
    super(delegate);
  }

  @Override
  public String tableToClassName(TableIdentifier tableIdentifier) {
    final String className = super.tableToClassName(tableIdentifier);
    return className + "Entity";
  }

  @Override
  public String columnToHibernateTypeName(TableIdentifier table, String columnName, int sqlType,
      int length, int precision, int scale, boolean nullable, boolean generatedIdentifier) {
    if (columnName.endsWith("_id")) {
      return "UUID";
    }
    return super.columnToHibernateTypeName(table, columnName, sqlType, length, precision, scale,
        nullable, generatedIdentifier);
  }

  @Override
  public String getTableIdentifierStrategyName(TableIdentifier identifier) {
    return org.hibernate.id.UUIDGenerator.class.getName();
  }
}
