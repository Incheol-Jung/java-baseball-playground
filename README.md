## [NEXTSTEP 플레이그라운드의 미션 진행 과정](https://github.com/next-step/nextstep-docs/blob/master/playground/README.md)

---
## 학습 효과를 높이기 위해 추천하는 미션 진행 방법

---
1. 피드백 강의 전까지 미션 진행 
> 피드백 강의 전까지 혼자 힘으로 미션 진행. 미션을 진행하면서 하나의 작업이 끝날 때 마다 add, commit
> 예를 들어 다음 숫자 야구 게임의 경우 0, 1, 2단계까지 구현을 완료한 후 push

![mission baseball](https://raw.githubusercontent.com/next-step/nextstep-docs/master/playground/images/mission_baseball.png)

---
2. 피드백 앞 단계까지 미션 구현을 완료한 후 피드백 강의를 학습한다.

---
3. Git 브랜치를 master 또는 main으로 변경한 후 피드백을 반영하기 위한 새로운 브랜치를 생성한 후 처음부터 다시 미션 구현을 도전한다.

```
git branch -a // 모든 로컬 브랜치 확인
git checkout master // 기본 브랜치가 master인 경우
git checkout main // 기본 브랜치가 main인 경우

git checkout -b 브랜치이름
ex) git checkout -b apply-feedback
```
4. 숫자야구게임 요구사항 목록 정리

- [x] 시스템은 세 가지 번호를 랜덤 생성한다
  - [x] 생성된 번호는 중복되면 안된다. 
- [x] 사용자에게 숫자 입력을 받는다.
  - [x] 숫자는 세 개를 입력받는다. 
  - [x] 숫자는 중복되면 안된다. 
  - [ ] 숫자는 0~9 이내만 가능하다. 
- [ ] 같은 수가 같은 자리에 있으면 스트라이크
- [ ] 다른 자리에 있으면 볼
- [ ] 같은 수가 전혀 없으면 낫싱
- [ ] 컴퓨터가 선택한 숫자를 모두 맞히면 게임이 종료된다. 
