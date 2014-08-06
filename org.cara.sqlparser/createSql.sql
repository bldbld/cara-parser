-- Create table
create table SHTCSND
(
  ID            CHAR(16) not null,
  BRNO          CHAR(3) not null,
  SND_ST_BRNO   CHAR(12) not null,
  FINREFNO      CHAR(20),
  CPGREFNO      CHAR(20),
  EXREFNO       CHAR(20),
  SEQNO         CHAR(32),
  SMPDATE       CHAR(8) not null,
  TXDATE        CHAR(8) not null,
  TYPE          CHAR(1),
  SOURCE        CHAR(1),
  DEPT_ID       CHAR(3),
  MD_TLRNO      CHAR(20),
  MD_TIME       CHAR(14),
  APV_TLRNO     CHAR(20),
  APV_TIME      CHAR(14),
  CHK_TLRNO     CHAR(20),
  CHK_TIME      CHAR(14),
  TRSC_STATUS   CHAR(2),
  FINSTS        CHAR(2),
  TADM_FLAG     CHAR(1),
  BLACKFLG      CHAR(1),
  NRA_FLG       CHAR(1),
  FIN_FLAG      CHAR(1),
  REASONDESC    VARCHAR2(256),
  FEECHARGEFLAG CHAR(1),
  FEEFLAG       CHAR(1),
  FEEAMOUNT     NUMBER(15),
  FEESTATUS     CHAR(1),
  FEEFINREFNO   CHAR(20),
  FEECPGREFNO   CHAR(20),
  FEE_TADM_FLAG CHAR(1),
  FILE_NAME     VARCHAR2(256),
  I2STS         CHAR(1),
  I2NO          CHAR(8),
  I2DATE        CHAR(8),
  RE_SND_TLRNO  CHAR(20),
  RE_SND_TIME   CHAR(14),
  OLD_SMPDATE   CHAR(8),
  OLD_ID        CHAR(16),
  OLD_SEQNO     CHAR(32),
  OLD_REFNO     CHAR(30),
  CRAD_FLG      CHAR(1),
  DBAD_FLG      CHAR(1),
  BENEAD_FLG    CHAR(1),
  RSV1          VARCHAR2(60),
  RSV2          VARCHAR2(60),
  RSV3          VARCHAR2(60),
  RSV4          VARCHAR2(60),
  RSV5          VARCHAR2(60)
)