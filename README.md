# Algo-Data

이 레포는 algo 데이터베이스를 위한 **SQL 스크립트 아카이브**입니다.<br>
스키마(DDL), 조회용 쿼리, 샘플 데이터를 분리해서 관리합니다.

### 📦 프로젝트 구조
```
📂 .
├── 📂 ddl/        # 테이블/제약조건 등 스키마 정의 (DDL)
├── 📂 queries/    # 조회용 SELECT 쿼리 모음
└── 📂 samples/    # 샘플 데이터 삽입 스크립트
```

### 🧱 ddl/ 상세

`ddl_script.sql`은 최종 실행용 전체 통합본이며, `modules/` 폴더에는 관리 및 유지보수를 위해 모듈별 스크립트가 분리되어 있습니다. 실행 순서를 보장하기 위해 각 파일에는 번호(prefix)가 붙어 있습니다.

```
📂 ddl/
├── 📄 ddl_script.sql        # 전체 통합본 (최종 실행용)
└── 📂 modules/              # 모듈별 DDL
    ├── 📄 00_init.sql
    ├── 📄 01_member_tables.sql
    ├── 📄 02_algo_tables.sql
    ├── 📄 03_coding_tables.sql
    ├── 📄 04_career_tables.sql
    ├── 📄 05_likes_tables.sql
    ├── 📄 06_study_tables.sql
    └── 📄 07_report_tables.sql
```