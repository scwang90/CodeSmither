{  "tables":[<#list tables as table>    {      "name": "${table.name}",      "newName": "${table.nameSql}",      "comment": "${table.comment}",      "newComment": "${table.nameSqlInStr}",      "columns": [      <#list table.columns as column>        {          "name": "${column.name}",          "newName": "${column.nameSql}",          "comment": "${column.comment}",          "newComment": "${column.nameSqlInStr}",          "type": "${column.typeJdbc}",          "length": ${column.length?c},          "nullable": ${column.nullable?c}        }<#if column_has_next>,</#if>      </#list>      ]    }<#if table_has_next>,</#if></#list>  ]}