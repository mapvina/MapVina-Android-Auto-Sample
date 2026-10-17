# MapVina-Android-Auto-Sample: verification 04/10/2026

**Status: BUILD BLOCKED.**

Sample coordinates corrected to core `1.0.2` and navigation-ui-android `1.0.0`.

Source baseline: `1f3c2f3c92fa8dbaf43a1878738c31ed39f9751e` (`origin/main` plus the sample/doc changes in this PR).

Audit only: no SDK API/version changes, tags, publishing or main merge.

## Android Auto/AAOS chưa nghiệm thu

Version catalog chỉ sửa coordinates public có thật:
`io.github.mapvina:android-sdk:1.0.2` và
`io.github.mapvina.navigation:navigation-ui-android:1.0.0`.

Set `AUDIT` to this report's `evidence` directory (absolute path). The init script
removes Maven local from dependency repositories; it does not alter SDK source.

```bash
export AUDIT="$(pwd)/docs/verification/2026-10-04/evidence"
./gradlew -I "$AUDIT/public-repositories.gradle" :app:assembleDebug --console=plain
```

Public-only build BLOCKED: transitive SpatialK/Kotlin modules có metadata `2.3.0`,
sample Kotlin compiler `2.1.0`. Không tăng compiler hoặc thay navigation code
để che lỗi ngoài phạm vi docs. Head unit, AAOS, logo runtime và navigation:
NOT TESTED; không dùng sample này như production integration reference.

## Evidence and acceptance boundary

- [android-auto-public.log](evidence/android-auto-public.log)

[Cross-repository artifact and runtime audit](https://github.com/mapvina/mapvina-document-android/blob/codex/docs-verification-20261004/docs/verification/2026-10-04/README.md).

PASS applies only to the named checks. BLOCKED is a reproduced failure; NOT TESTED is not a pass.

## Separate follow-up

[Reproduced blocker issue](https://github.com/mapvina/MapVina-Android-Auto-Sample/issues/2). SDK/infra fixes are outside this PR.
