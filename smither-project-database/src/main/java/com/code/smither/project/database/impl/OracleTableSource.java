package com.code.smither.project.database.impl;

import com.code.smither.project.base.ProjectConfig;
import com.code.smither.project.base.constant.Database;
import com.code.smither.project.database.api.DbFactory;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Oracle 数据库 表源
 * Created by SCWANG on 2016/8/1.
 */
public class OracleTableSource extends DbTableSource implements Database {

    public OracleTableSource(ProjectConfig config, DbFactory dbFactory) {
        this(config, dbFactory, false);
    }

    public OracleTableSource(ProjectConfig config, DbFactory dbFactory, boolean autoclose) {
        super(config, dbFactory, autoclose);
    }

    @Override
    public String name() {
        return "oracle";
    }

    @Override
    public Database getDatabase() {
        return this;
    }

    @Override
    public boolean isKeyword(String value) {
        for (String keyword : keywords) {
            if (keyword.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String wrapperKeyword(String name) {
        return name;
//        return '"' + name.toUpperCase() + '"';
    }


    private static final String[] keywords = "ABORT,ACCESS,ACCESSED,ACCOUNT,ACTIVATE,ADD,ADMIN,ADMINISTER,ADMINISTRATOR,ADVISE,ADVISOR,AFTER,ALGORITHM,ALIAS,ALL,ALLOCATE,ALLOW,ALL_ROWS,ALTER,ALWAYS,ANALYZE,ANCILLARY,AND,AND_EQUAL,ANTIJOIN,ANY,APPEND,APPLY,ARCHIVE,ARCHIVELOG,ARRAY,AS,ASC,ASSOCIATE,AT,ATTRIBUTE,ATTRIBUTES,AUDIT,AUTHENTICATED,AUTHENTICATION,AUTHID,AUTHORIZATION,AUTO,AUTOALLOCATE,AUTOEXTEND,AUTOMATIC,AVAILABILITY,BACKUP,BECOME,BEFORE,BEGIN,BEHALF,BETWEEN,BFILE,BIGFILE,BINARY_DOUBLE,BINARY_DOUBLE_INFINITY,BINARY_DOUBLE_NAN,BINARY_FLOAT,BINARY_FLOAT_INFINITY,BINARY_FLOAT_NAN,BINDING,BITMAP,BITS,BLOB,BLOCK,BLOCKS,BLOCKSIZE,BLOCK_RANGE,BODY,BOTH,BOUND,BROADCAST,BUFFER,BUFFER_CACHE,BUFFER_POOL,BUILD,BULK,BY,BYPASS_RECURSIVE_CHECK,BYPASS_UJVC,BYTE,CACHE,CACHE_CB,CACHE_INSTANCES,CACHE_TEMP_TABLE,CALL,CANCEL,CARDINALITY,CASCADE,CASE,CAST,CATEGORY,CERTIFICATE,CFILE,CHAINED,CHANGE,CHAR,CHARACTER,CHAR_CS,CHECK,CHECKPOINT,CHILD,CHOOSE,CHUNK,CIV_GB,CLASS,CLEAR,CLOB,CLONE,CLOSE,CLOSE_CACHED_OPEN_CURSORS,CLUSTER,CLUSTERING_FACTOR,COALESCE,COARSE,COLLECT,COLLECTIONS_GET_REFS,COLUMN,COLUMNS,COLUMN_STATS,COLUMN_VALUE,COMMENT,COMMIT,COMMITTED,COMPACT,COMPATIBILITY,COMPILE,COMPLETE,COMPOSITE_LIMIT,COMPRESS,COMPUTE,CONFORMING,CONNECT,CONNECT_BY_ISCYCLE,CONNECT_BY_ISLEAF,CONNECT_BY_ROOT,CONNECT_TIME,CONSIDER,CONSISTENT,CONSTRAINT,CONSTRAINTS,CONTAINER,CONTENT,CONTENTS,CONTEXT,CONTINUE,CONTROLFILE,CONVERT,CORRUPTION,COST,CPU_COSTING,CPU_PER_CALL,CPU_PER_SESSION,CREATE,CREATE_STORED_OUTLINES,CROSS,CUBE,CUBE_GB,CURRENT,CURRENT_DATE,CURRENT_SCHEMA,CURRENT_TIME,CURRENT_TIMESTAMP,CURRENT_USER,CURSOR,CURSOR_SHARING_EXACT,CURSOR_SPECIFIC_SEGMENT,CYCLE,DANGLING,DATA,DATABASE,DATAFILE,DATAFILES,DATAOBJNO,DATE,DATE_MODE,DAY,DBA,DBA_RECYCLEBIN,DBTIMEZONE,DDL,DEALLOCATE,DEBUG,DEC,DECIMAL,DECLARE,DECREMENT,DEFAULT,DEFERRABLE,DEFERRED,DEFINED,DEFINER,DEGREE,DELAY,DELETE,DEMAND,DENSE_RANK,DEREF,DEREF_NO_REWRITE,DESC,DETACHED,DETERMINES,DICTIONARY,DIMENSION,DIRECTORY,DISABLE,DISASSOCIATE,DISCONNECT,DISK,DISKGROUP,DISKS,DISMOUNT,DISTINCT,DISTINGUISHED,DISTRIBUTED,DML,DML_UPDATE,DOCUMENT,DOMAIN_INDEX_NO_SORT,DOMAIN_INDEX_SORT,DOUBLE,DOWNGRADE,DRIVING_SITE,DROP,DUMP,DYNAMIC,DYNAMIC_SAMPLING,DYNAMIC_SAMPLING_EST_CDN,EACH,ELEMENT,ELSE,EMPTY,ENABLE,ENCRYPTED,ENCRYPTION,END,ENFORCE,ENFORCED,ENTRY,ERROR,ERROR_ON_OVERLAP_TIME,ESCAPE,ESTIMATE,EVENTS,EXCEPT,EXCEPTIONS,EXCHANGE,EXCLUDING,EXCLUSIVE,EXECUTE,EXEMPT,EXISTS,EXPAND_GSET_TO_UNION,EXPIRE,EXPLAIN,EXPLOSION,EXPORT,EXPR_CORR_CHECK,EXTEND,EXTENDS,EXTENT,EXTENTS,EXTERNAL,EXTERNALLY,EXTRACT,FACT,FAILED,FAILED_LOGIN_ATTEMPTS,FAILGROUP,FALSE,FAST,FBTSCAN,FIC_CIV,FIC_PIV,FILE,FILTER,FINAL,FINE,FINISH,FIRST,FIRST_ROWS,FLAGGER,FLASHBACK,FLOAT,FLOB,FLUSH,FOLLOWING,FOR,FORCE,FORCE_XML_QUERY_REWRITE,FOREIGN,FREELIST,FREELISTS,FREEPOOLS,FRESH,FROM,FULL,FUNCTION,FUNCTIONS,GATHER_PLAN_STATISTICS,GBY_CONC_ROLLUP,GENERATED,GLOBAL,GLOBALLY,GLOBAL_NAME,GLOBAL_TOPIC_ENABLED,GRANT,GROUP,GROUPING,GROUPS,GROUP_BY,GUARANTEE,GUARANTEED,GUARD,HASH,HASHKEYS,HASH_AJ,HASH_SJ,HAVING,HEADER,HEAP,HIERARCHY,HIGH,HINTSET_BEGIN,HINTSET_END,HOUR,HWM_BROKERED,ID,IDENTIFIED,IDENTIFIER,IDENTITY,IDGENERATORS,IDLE_TIME,IF,IGNORE,IGNORE_ON_CLAUSE,IGNORE_OPTIM_EMBEDDED_HINTS,IGNORE_WHERE_CLAUSE,IMMEDIATE,IMPORT,IN,INCLUDE_VERSION,INCLUDING,INCREMENT,INCREMENTAL,INDEX,INDEXED,INDEXES,INDEXTYPE,INDEXTYPES,INDEX_ASC,INDEX_COMBINE,INDEX_DESC,INDEX_FFS,INDEX_FILTER,INDEX_JOIN,INDEX_ROWS,INDEX_RRS,INDEX_SCAN,INDEX_SKIP_SCAN,INDEX_SS,INDEX_SS_ASC,INDEX_SS_DESC,INDEX_STATS,INDICATOR,INFINITE,INFORMATIONAL,INITIAL,INITIALIZED,INITIALLY,INITRANS,INLINE,INNER,INSERT,INSTANCE,INSTANCES,INSTANTIABLE,INSTANTLY,INSTEAD,INT,INTEGER,INTEGRITY,INTERMEDIATE,INTERNAL_CONVERT,INTERNAL_USE,INTERPRETED,INTERSECT,INTERVAL,INTO,INVALIDATE,IN_MEMORY_METADATA,IS,ISOLATION,ISOLATION_LEVEL,ITERATE,ITERATION_NUMBER,JAVA,JOB,JOIN,KEEP,KERBEROS,KEY,KEYFILE,KEYS,KEYSIZE,KEY_LENGTH,KILL,LAST,LATERAL,LAYER,LDAP_REGISTRATION,LDAP_REGISTRATION_ENABLED,LDAP_REG_SYNC_INTERVAL,LEADING,LEFT,LENGTH,LESS,LEVEL,LEVELS,LIBRARY,LIKE,LIKE,LIKE,LIKEC,LIKE_EXPAND,LIMIT,LINK,LIST,LOB,LOCAL,LOCALTIME,LOCALTIMESTAMP,LOCAL_INDEXES,LOCATION,LOCATOR,LOCK,LOCKED,LOG,LOGFILE,LOGGING,LOGICAL,LOGICAL_READS_PER_CALL,LOGICAL_READS_PER_SESSION,LOGOFF,LOGON,LONG,MAIN,MANAGE,MANAGED,MANAGEMENT,MANUAL,MAPPING,MASTER,MATCHED,MATERIALIZE,MATERIALIZED,MAX,MAXARCHLOGS,MAXDATAFILES,MAXEXTENTS,MAXIMIZE,MAXINSTANCES,MAXLOGFILES,MAXLOGHISTORY,MAXLOGMEMBERS,MAXSIZE,MAXTRANS,MAXVALUE,MEASURES,MEMBER,MEMORY,MERGE,MERGE_AJ,MERGE_CONST_ON,MERGE_SJ,METHOD,MIGRATE,MIN,MINEXTENTS,MINIMIZE,MINIMUM,MINUS,MINUTE,MINVALUE,MIRROR,MLSLABEL,MODE,MODEL,MODEL_DONTVERIFY_UNIQUENESS,MODEL_MIN_ANALYSIS,MODEL_NO_ANALYSIS,MODEL_PBY,MODEL_PUSH_REF,MODIFY,MONITORING,MONTH,MOUNT,MOVE,MOVEMENT,MULTISET,MV_MERGE,NAME,NAMED,NAN,NATIONAL,NATIVE,NATURAL,NAV,NCHAR,NCHAR_CS,NCLOB,NEEDED,NESTED,NESTED_TABLE_FAST_INSERT,NESTED_TABLE_GET_REFS,NESTED_TABLE_ID,NESTED_TABLE_SET_REFS,NESTED_TABLE_SET_SETID,NETWORK,NEVER,NEW,NEXT,NLS_CALENDAR,NLS_CHARACTERSET,NLS_COMP,NLS_CURRENCY,NLS_DATE_FORMAT,NLS_DATE_LANGUAGE,NLS_ISO_CURRENCY,NLS_LANG,NLS_LANGUAGE,NLS_LENGTH_SEMANTICS,NLS_NCHAR_CONV_EXCP,NLS_NUMERIC_CHARACTERS,NLS_SORT,NLS_SPECIAL_CHARS,NLS_TERRITORY,NL_AJ,NL_SJ,NO,NOAPPEND,NOARCHIVELOG,NOAUDIT,NOCACHE,NOCOMPRESS,NOCPU_COSTING,NOCYCLE,NODELAY,NOFORCE,NOGUARANTEE,NOLOGGING,NOMAPPING,NOMAXVALUE,NOMINIMIZE,NOMINVALUE,NOMONITORING,NONE,NOORDER,NOOVERRIDE,NOPARALLEL,NOPARALLEL_INDEX,NORELY,NOREPAIR,NORESETLOGS,NOREVERSE,NOREWRITE,NORMAL,NOROWDEPENDENCIES,NOSEGMENT,NOSORT,NOSTRICT,NOSWITCH,NOT,NOTHING,NOVALIDATE,NOWAIT,NO_ACCESS,NO_BASETABLE_MULTIMV_REWRITE,NO_BUFFER,NO_CPU_COSTING,NO_EXPAND,NO_EXPAND_GSET_TO_UNION,NO_FACT,NO_FILTERING,NO_INDEX,NO_INDEX_FFS,NO_INDEX_SS,NO_MERGE,NO_MODEL_PUSH_REF,NO_MONITORING,NO_MULTIMV_REWRITE,NO_ORDER_ROLLUPS,NO_PARALLEL,NO_PARALLEL_INDEX,NO_PARTIAL_COMMIT,NO_PRUNE_GSETS,NO_PUSH_PRED,NO_PUSH_SUBQ,NO_QKN_BUFF,NO_QUERY_TRANSFORMATION,NO_REF_CASCADE,NO_REWRITE,NO_SEMIJOIN,NO_SET_TO_JOIN,NO_STAR_TRANSFORMATION,NO_STATS_GSETS,NO_SWAP_JOIN_INPUTS,NO_TRIGGER,NO_UNNEST,NO_USE_HASH,NO_USE_MERGE,NO_USE_NL,NO_XML_QUERY_REWRITE,NULL,NULLS,NUMBER,NUMERIC,NVARCHAR,OBJECT,OBJNO,OBJNO_REUSE,OF,OFF,OFFLINE,OID,OIDINDEX,OLD,ON,ONLINE,ONLY,OPAQUE,OPAQUE_TRANSFORM,OPAQUE_XCANONICAL,OPCODE,OPEN,OPERATOR,OPTIMAL,OPTIMIZER_FEATURES_ENABLE,OPTIMIZER_GOAL,OPTION,OPT_ESTIMATE,OR,ORA_ROWSCN,ORDER,ORDERED,ORDERED_PREDICATES,ORGANIZATION,OR_EXPAND,OUTER,OUTLINE,OUT_OF_LINE,OVER,OVERFLOW,OVERFLOW_NOMOVE,OVERLAPS,OWN,PACKAGE,PACKAGES,PARALLEL,PARALLEL_INDEX,PARAMETERS,PARENT,PARITY,PARTIALLY,PARTITION,PARTITIONS,PARTITION_HASH,PARTITION_LIST,PARTITION_RANGE,PASSWORD,PASSWORD_GRACE_TIME,PASSWORD_LIFE_TIME,PASSWORD_LOCK_TIME,PASSWORD_REUSE_MAX,PASSWORD_REUSE_TIME,PASSWORD_VERIFY_FUNCTION,PCTFREE,PCTINCREASE,PCTTHRESHOLD,PCTUSED,PCTVERSION,PERCENT,PERFORMANCE,PERMANENT,PFILE,PHYSICAL,PIV_GB,PIV_SSF,PLAN,PLSQL_CODE_TYPE,PLSQL_DEBUG,PLSQL_OPTIMIZE_LEVEL,PLSQL_WARNINGS,POLICY,POST_TRANSACTION,POWER,PQ_DISTRIBUTE,PQ_MAP,PQ_NOMAP,PREBUILT,PRECEDING,PRECISION,PREPARE,PRESENT,PRESERVE,PRIMARY,PRIOR,PRIVATE,PRIVATE_SGA,PRIVILEGE,PRIVILEGES,PROCEDURE,PROFILE,PROGRAM,PROJECTPROTECTED,PROTECTION,PUBLIC,PURGE,PUSH_PRED,PUSH_SUBQ,PX_GRANULE,QB_NAME,QUERY,QUERY_BLOCK,QUEUE,QUEUE_CURR,QUEUE_ROWP,QUIESCE,QUOTA,RANDOM,RANGE,RAPIDLY,RAW,RBA,READ,READS,REAL,REBALANCE,REBUILD,RECORDS_PER_BLOCK,RECOVER,RECOVERABLE,RECOVERY,RECYCLE,RECYCLEBIN,REDUCED,REDUNDANCY,REF,REFERENCE,REFERENCED,REFERENCES,REFERENCING,REFRESH,REF_CASCADE_CURSOR,REGEXP_LIKE,REGISTER,REJECT,REKEY,RELATIONAL,RELY,REMOTE_MAPPED,RENAME,REPAIR,REPLACE,REQUIRED,RESET,RESETLOGS,RESIZE,RESOLVE,RESOLVER,RESOURCE,RESTORE_AS_INTERVALS,RESTRICT,RESTRICTED,RESTRICT_ALL_REF_CONS,RESUMABLE,RESUME,RETENTION,RETURN,RETURNING,REUSE,REVERSE,REVOKE,REWRITE,REWRITE_OR_ERROR,RIGHT,ROLE,ROLES,ROLLBACK,ROLLUP,ROW,ROWDEPENDENCIES,ROWID,ROWNUM,ROWS,ROW_LENGTH,RULE,RULES,SAMPLE,SAVEPOINT,SAVE_AS_INTERVALS,SB,SCALE,SCALE_ROWS,SCAN,SCAN_INSTANCES,SCHEDULER,SCHEMA,SCN,SCN_ASCENDING,SCOPE,SD_ALL,SD_INHIBIT,SD_SHOW,SECOND,SECURITY,SEED,SEGMENT,SEG_BLOCK,SEG_FILE,SELECT,SELECTIVITY,SEMIJOIN,SEMIJOIN_DRIVER,SEQUENCE,SEQUENCED,SEQUENTIAL,SERIALIZABLE,SERVERERROR,SESSION,SESSIONS_PER_USER,SESSIONTIMEZONE,SESSIONTZNAME,SESSION_CACHED_CURSORS,SET,SETS,SETTINGS,SET_TO_JOIN,SEVERE,SHARE,SHARED,SHARED_POOL,SHRINK,SHUTDOWN,SIBLINGS,SID,SIMPLE,SINGLE,SINGLETASK,SIZE,SKIP,SKIP_EXT_OPTIMIZER,SKIP_UNQ_UNUSABLE_IDX,SKIP_UNUSABLE_INDEXES,SMALLFILE,SMALLINT,SNAPSHOT,SOME,SORT,SOURCE,SPACE,SPECIFICATION,SPFILE,SPLIT,SPREADSHEET,SQL,SQLLDR,SQL_TRACE,STANDBY,STAR,START,STARTUP,STAR_TRANSFORMATION,STATEMENT_ID,STATIC,STATISTICS,STOP,STORAGE,STORE,STREAMS,STRICT,STRIP,STRUCTURE,SUBMULTISET,SUBPARTITION,SUBPARTITIONS,SUBPARTITION_REL,SUBSTITUTABLE,SUCCESSFUL,SUMMARY,SUPPLEMENTAL,SUSPEND,SWAP_JOIN_INPUTS,SWITCH,SWITCHOVER,SYNONYM,SYSAUX,SYSDATE,SYSDBA,SYSOPER,SYSTEM,SYSTIMESTAMP,SYS_DL_CURSOR,SYS_FBT_INSDEL,SYS_OP_BITVEC,SYS_OP_CAST,SYS_OP_COL_PRESENT,SYS_OP_ENFORCE_NOT_NULL$,SYS_OP_MINE_VALUE,SYS_OP_NOEXPAND,SYS_OP_NTCIMG$,SYS_PARALLEL_TXN,SYS_RID_ORDER,TABLE,TABLES,TABLESPACE,TABLESPACE_NO,TABLE_STATS,TABNO,TEMPFILE,TEMPLATE,TEMPORARY,TEST,THAN,THE,THEN,THREAD,THROUGH,TIME,TIMEOUT,TIMESTAMP,TIMEZONE_ABBR,TIMEZONE_HOUR,TIMEZONE_MINUTE,TIMEZONE_REGION,TIME_ZONE,TIV_GB,TIV_SSF,TO,TOPLEVEL,TRACE,TRACING,TRACKING,TRAILING,TRANSACTION,TRANSITIONAL,TREAT,TRIGGER,TRIGGERS,TRUE,TRUNCATE,TRUSTED,TUNING,TX,TYPE,TYPES,TZ_OFFSET,UB,UBA,UID,UNARCHIVED,UNBOUND,UNBOUNDED,UNDER,UNDO,UNDROP,UNIFORM,UNION,UNIQUE,UNLIMITED,UNLOCK,UNNEST,UNPACKED,UNPROTECTED,UNQUIESCE,UNRECOVERABLE,UNTIL,UNUSABLE,UNUSED,UPDATABLE,UPDATE,UPDATED,UPD_INDEXES,UPD_JOININDEX,UPGRADE,UPSERT,UROWID,USAGE,USE,USER,USER_DEFINED,USER_RECYCLEBIN,USE_ANTI,USE_CONCAT,USE_HASH,USE_MERGE,USE_NL,USE_NL_WITH_INDEX,USE_PRIVATE_OUTLINES,USE_SEMI,USE_STORED_OUTLINES,USE_TTT_FOR_GSETS,USE_WEAK_NAME_RESL,USING,VALIDATE,VALIDATION,VALUE,VALUES,VARCHAR,VARCHAR,VARRAY,VARYING,VECTOR_READ,VECTOR_READ_TRACE,VERSION,VERSIONS,VIEW,WAIT,WELLFORMED,WHEN,WHENEVER,WHERE,WHITESPACE,WITH,WITHIN,WITHOUT,WORK,WRITE,XID,XMLATTRIBUTES,XMLCOLATTVAL,XMLELEMENT,XMLFOREST,XMLPARSE,XMLSCHEMA,XMLTYPE,X_DYN_PRUNE,YEAR,ZONE".split(",");

    @Override
    protected ResultSet queryTables(DatabaseMetaData metaData) throws SQLException {
        return metaData.getTables(null, dbFactory.getUser(), "%", new String[] { "TABLE" });
    }

    @Override
    protected ResultSet queryColumns(DatabaseMetaData metaData, String tableName) throws SQLException {
        return metaData.getColumns(null, dbFactory.getUser(), tableName, "%");
    }

    @Override
    protected ResultSet queryPrimaryKeys(DatabaseMetaData metaData, String tableName) throws SQLException {
        return metaData.getPrimaryKeys(null, dbFactory.getUser(), tableName);
    }
}
