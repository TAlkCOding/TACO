function categoryChange1(localParent) {
  var a = [
    "강남구",
    "강동구",
    "강북구",
    "강서구",
    "관악구",
    "광진구",
    "구로구",
    "금천구",
    "노원구",
    "도봉구",
    "동대문구",
    "동작구",
    "마포구",
    "서대문구",
    "서초구",
    "성동구",
    "성북구",
    "송파구",
    "양천구",
    "영등포구",
    "용산구",
    "은평구",
    "종로구",
    "중구",
    "중랑구",
  ];
  /*var b = ["광산구", "남구", "동구", "북구", "서구"];
  var c = [
    "남구",
    "공주시",
    "보령시",
    "서산시",
    "아산시",
    "천안시",
    "금산군",
    "논산군",
    "당진군",
    "부여군",
    "서천군",
    "연기군",
    "예산군",
    "청양군",
    "태안군",
    "홍성군",
  ];
  */
  var target = document.getElementById("localChildOne");
  var removeAll = document.getElementById("localChildTwo");

  if (localParent.value == "a") var localChildOne = a;
  else if (localParent.value == "b") var localChildOne = b;
  else if (localParent.value == "c") var localChildOne = c;

  target.options.length = 1;
  removeAll.options.length = 1;

  for (x in localChildOne) {
    var opt = document.createElement("option");
    opt.value = localChildOne[x];
    opt.innerHTML = localChildOne[x];
    target.appendChild(opt);
  }
}

function categoryChange2(localChildOne) {
  if (localParent.value == "a") {
    var 강남구 = [
      "율현동",
      "도곡1동",
      "대치2동",
      "일원본동",
      "대치4동",
      "역삼2동",
      "개포1동",
      "대치동",
      "세곡동",
      "자곡동",
      "삼성2동",
      "삼성동",
      "신사동",
      "역삼1동",
      "일원동",
      "대치1동",
      "압구정동",
      "논현1동",
      "개포3동",
      "개포4동",
      "삼성1동",
      "논현동",
      "일원1동",
      "청담동",
      "역삼동",
      "도곡동",
      "논현2동",
      "도곡2동",
      "수서동",
    ];
    var 강동구 = [
      "강일동",
      "명일동",
      "천호제3동",
      "고덕제2동",
      "둔촌동",
      "암사동",
      "길동",
      "명일제2동",
      "둔촌제1동",
      "천호제2동",
      "암사제1동",
      "상일동",
      "명일제1동",
      "둔촌제2동",
      "성내제1동",
      "성내제3동",
      "암사제2동",
      "고덕동",
      "고덕제1동",
      "천호제1동",
      "성내제2동",
      "암사제3동",
      "천호동",
      "성내동",
    ];
    var 강북구 = [
      "번1동",
      "미아동",
      "수유2동",
      "송중동",
      "수유3동",
      "우이동",
      "수유동",
      "번2동",
      "삼양동",
      "인수동",
      "번3동",
      "송천동",
      "수유1동",
      "번동",
      "삼각산동",
    ];
    var 강서구 = [
      "등촌동",
      "화곡제3동",
      "개화동",
      "방화동",
      "오곡동",
      "방화제3동",
      "가양제2동",
      "외발산동",
      "방화제1동",
      "마곡동",
      "방화제2동",
      "화곡제1동",
      "화곡제6동",
      "화곡제4동",
      "화곡제8동",
      "화곡본동",
      "화곡제2동",
      "등촌제3동",
      "가양제1동",
      "염창동",
      "우장산동",
      "발산제1동",
      "화곡동",
      "공항동",
      "오쇠동",
      "등촌제2동",
      "과해동",
      "가양제3동",
      "가양동",
      "등촌제1동",
      "내발산동",
    ];
    var 관악구 = [
      "신림동",
      "난향동",
      "신원동",
      "성현동",
      "청룡동",
      "낙성대동",
      "봉천동",
      "보라매동",
      "신사동",
      "은천동",
      "서림동",
      "난곡동",
      "서원동",
      "중앙동",
      "대학동",
      "행운동",
      "조원동",
      "인헌동",
      "청림동",
      "삼성동",
      "미성동",
      "남현동",
    ];
    var 광진구 = [
      "구의제3동",
      "구의제1동",
      "능동",
      "구의제2동",
      "자양동",
      "화양동",
      "중곡제4동",
      "중곡동",
      "자양제4동",
      "구의동",
      "자양제1동",
      "자양제2동",
      "자양제3동",
      "군자동",
      "중곡제1동",
      "중곡제3동",
      "중곡제2동",
      "광장동",
    ];
    var 구로구 = [
      "오류동",
      "구로제5동",
      "개봉동",
      "궁동",
      "고척제2동",
      "오류제1동",
      "천왕동",
      "수궁동",
      "개봉제2동",
      "구로동",
      "구로제4동",
      "고척제1동",
      "신도림동",
      "온수동",
      "오류제2동",
      "항동",
      "개봉제1동",
      "개봉제3동",
      "구로제2동",
      "구로제3동",
      "가리봉동",
      "구로제1동",
    ];
    var 금천구 = ["지수", "제니", "로제", "리사"];
    var 노원구 = ["LE", "하니", "정화", "혜린", "솔지"];
    var 도봉구 = ["여기도", "몰라여"];
    var 동대문구 = ["여기는", "저도잘", "뭐가", "있지?"];

    var 동작구 = ["진월동", "주월동", "효덕동", "등등?", "ㅋㅋ"];
    var 마포구 = ["지수", "제니", "로제", "리사"];
    var 서대문구 = ["LE", "하니", "정화", "혜린", "솔지"];
    var 서초구 = ["여기도", "몰라여"];

    var 성동구 = ["진월동", "주월동", "효덕동", "등등?", "ㅋㅋ"];
    var 성북구 = ["지수", "제니", "로제", "리사"];
    var 송파구 = ["LE", "하니", "정화", "혜린", "솔지"];
    var 양천구 = ["여기도", "몰라여"];

    var 영등포구 = ["여기는", "저도잘", "뭐가", "있지?"];
    var 용산구 = ["진월동", "주월동", "효덕동", "등등?", "ㅋㅋ"];
    var 은평구 = ["지수", "제니", "로제", "리사"];
    var 종로구 = ["LE", "하니", "정화", "혜린", "솔지"];
    var 중구 = ["여기도", "몰라여"];
    var 중랑구 = ["여기는", "저도잘", "뭐가", "있지?"];
  } else if (localParent.value == "c") {
    var 남구 = ["빅토리아", "엠버", "루나", "크리스탈"];
    var 공주시 = ["LE", "하니", "정화", "혜린", "솔지"];
  }
  var target = document.getElementById("localChildTwo");

  if (localChildOne.value == "남구") var localChildTwo = 남구;
  else if (localChildOne.value == "북구") var localChildTwo = 북구;
  else if (localChildOne.value == "서구") var localChildTwo = 서구;
  else if (localChildOne.value == "동구") var localChildTwo = 동구;
  else if (localChildOne.value == "광산구") var localChildTwo = 광산구;

  target.options.length = 1;

  for (x in localChildTwo) {
    var opt = document.createElement("option");
    opt.value = localChildTwo[x];
    opt.innerHTML = localChildTwo[x];
    target.appendChild(opt);
  }
}

/*자동 슬라이드*/
function move() {
  var curIndex = 0;
  let one = document.getElementById("newsCircle1");
  let two = document.getElementById("newsCircle2");
  let three = document.getElementById("newsCircle3");

  document.querySelector("#newsCircle2").addEventListener("click", function () {
    document.querySelector(".newsContainer").style.transform =
      "translate(-100vw)";
    return (curIndex = 0);
  });

  document.querySelector("#newsCircle3").addEventListener("click", function () {
    document.querySelector(".newsContainer").style.transform =
      "translate(-200vw)";
    return (curIndex = 1);
  });

  document.querySelector("#newsCircle1").addEventListener("click", function () {
    document.querySelector(".newsContainer").style.transform = "translate(0vw)";
    return (curIndex = -1);
  });

  setInterval(function () {
    document.querySelector(".newsContainer").style.transition = "0.2s";
    document.querySelector(".newsContainer").style.transform =
      "translate3d(-" + 100 * (curIndex + 1) + "vw, 0px, 0px)";

    curIndex++;

    if (curIndex === 0) {
      one.className = "click";
    } else {
      one.className = "nonClick";
    }

    if (curIndex === 1) {
      two.className = "click";
    } else {
      two.className = "nonClick";
    }

    if (curIndex === 2) {
      three.className = "click";
    } else {
      three.className = "nonClick";
    }

    if (curIndex === 2) {
      curIndex = -1;
    }
  }, 3000);
}

document.addEventListener("DOMContentLoaded", function () {
  move();
});

/* 
let interval = move();
/*버튼 색 변경
const nonClick = document.querySelectorAll(".nonClick");

function handleClick(event) {
  clearInterval(interval);
  // div에서 모든 "click" 클래스 제거
  nonClick.forEach((e) = {
    e.classList.remove("click");
  });
  // 클릭한 div만 "click"클래스 추가
  event.target.classList.add("click");
}

nonClick.forEach((e) = {
  e.addEventListener("click", handleClick);
  move();
});
*/
