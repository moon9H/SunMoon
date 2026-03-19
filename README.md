# SunMoon Memory

대화 리마인드 정적 웹을 Spring MVC + Neon(PostgreSQL) 기반으로 옮기기 위한 프로젝트입니다.

## 현재 상태

- Neon DB 연결 완료
- `GET /api/conversations` 목록 조회 완료
- `GET /api/conversations/{id}` 상세 조회 완료
- `GET /db/ping` 연결 확인용 endpoint 유지

## 주요 엔드포인트

- `GET /db/ping`
- `GET /api/conversations`
- `GET /api/conversations/{id}`

## 배포 환경 변수

- `SPRING_PROFILES_ACTIVE=db`
- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
