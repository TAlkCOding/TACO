function categoryChange1(localParent) {
  var 서울특별시 = [
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

  if (localParent.value == "서울특별시") var localChildOne = 서울특별시;
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
  if (localParent.value == "서울특별시") {
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
    var 금천구 = [
      "독산동",
      "독산제4동",
      "독산제2동",
      "시흥동",
      "시흥제3동",
      "시흥제5동",
      "독산제1동",
      "시흥제1동",
      "시흥제4동",
      "시흥제2동",
      "가산동",
      "독산제3동",
    ];
    var 노원구 = [
      "독산동",
      "독산제4동",
      "독산제2동",
      "시흥동",
      "시흥제3동",
      "시흥제5동",
      "독산제1동",
      "시흥제1동",
      "시흥제4동",
      "시흥제2동",
      "가산동",
      "독산제3동",
    ];
    var 도봉구 = [
      "방학제3동",
      "도봉제1동",
      "쌍문제3동",
      "쌍문제4동",
      "방학동",
      "쌍문제2동",
      "창제5동",
      "쌍문동",
      "창제1동",
      "방학제1동",
      "창제2동",
      "방학제2동",
      "창동",
      "쌍문제1동",
      "도봉동",
      "도봉제2동",
      "창제3동",
      "창제4동",
    ];
    var 동대문구 = [
      "답십리제2동",
      "전농제2동",
      "휘경제1동",
      "제기동",
      "전농제1동",
      "이문제1동",
      "휘경동",
      "이문동",
      "용두동",
      "전농동",
      "회기동",
      "신설동",
      "답십리제1동",
      "휘경제2동",
      "이문제2동",
      "장안제2동",
      "장안동",
      "장안제1동",
      "용신동",
      "청량리동",
      "답십리동",
    ];

    var 동작구 = [
      "신대방제2동",
      "상도제1동",
      "상도동",
      "상도제4동",
      "노량진동",
      "상도1동",
      "사당동",
      "사당제1동",
      "상도제3동",
      "노량진제2동",
      "노량진제1동",
      "흑석동",
      "사당제2동",
      "사당제3동",
      "상도제2동",
      "사당제4동",
      "대방동",
      "신대방제1동",
      "동작동",
      "사당제5동",
      "본동",
      "신대방동",
    ];
    var 마포구 = [
      "서교동",
      "중동",
      "용강동",
      "구수동",
      "신정동",
      "마포동",
      "성산제1동",
      "토정동",
      "하중동",
      "성산동",
      "신수동",
      "망원제2동",
      "공덕동",
      "신공덕동",
      "염리동",
      "현석동",
      "동교동",
      "상암동",
      "연남동",
      "망원동",
      "성산제2동",
      "대흥동",
      "상수동",
      "서강동",
      "합정동",
      "창전동",
      "망원제1동",
      "아현동",
      "당인동",
      "노고산동",
      "도화동",
    ];
    var 서대문구 = [
      "홍제제1동",
      "북가좌제2동",
      "창천동",
      "현저동",
      "홍은제2동",
      "연희동",
      "영천동",
      "미근동",
      "봉원동",
      "북가좌동",
      "충정로3가",
      "홍제제2동",
      "홍제제3동",
      "홍은제1동",
      "북가좌제1동",
      "대신동",
      "남가좌제2동",
      "충정로2가",
      "홍제동",
      "홍은동",
      "북아현동",
      "합동",
      "신촌동",
      "대현동",
      "충현동",
      "옥천동",
      "남가좌제1동",
      "천연동",
      "남가좌동",
      "냉천동",
    ];
    var 서초구 = [
      "서초3동",
      "방배1동",
      "방배4동",
      "반포동",
      "방배본동",
      "서초4동",
      "반포1동",
      "내곡동",
      "서초1동",
      "서초2동",
      "양재1동",
      "방배3동",
      "원지동",
      "양재동",
      "염곡동",
      "반포4동",
      "반포본동",
      "신원동",
      "반포2동",
      "우면동",
      "양재2동",
      "방배동",
      "잠원동",
      "반포3동",
      "방배2동",
      "서초동",
    ];

    var 성동구 = [
      "성수동2가",
      "왕십리도선동",
      "왕십리제2동",
      "행당제1동",
      "금호동2가",
      "사근동",
      "금호1가동",
      "응봉동",
      "용답동",
      "상왕십리동",
      "성수2가제1동",
      "도선동",
      "금호동1가",
      "송정동",
      "성수1가제2동",
      "성수2가제3동",
      "금호동3가",
      "금호2.3가동",
      "행당제2동",
      "하왕십리동",
      "홍익동",
      "성수1가제1동",
      "마장동",
      "옥수동",
      "성수동1가",
      "금호동4가",
      "금호4가동",
      "행당동",
    ];
    var 성북구 = [
      "성북동",
      "삼선동3가",
      "동소문동3가",
      "하월곡동",
      "길음동",
      "안암동3가",
      "종암동",
      "보문동1가",
      "동소문동5가",
      "보문동2가",
      "동선동5가",
      "정릉제4동",
      "월곡제1동",
      "보문동5가",
      "상월곡동",
      "안암동2가",
      "정릉제3동",
      "장위제1동",
      "돈암제1동",
      "동선동4가",
      "장위동",
      "정릉제1동",
      "동선동3가",
      "성북동1가",
      "정릉동",
      "삼선동1가",
      "동소문동1가",
      "길음제1동",
      "장위제3동",
      "길음제2동",
      "보문동4가",
      "삼선동4가",
      "삼선동2가",
      "보문동",
      "정릉제2동",
      "석관동",
      "안암동4가",
      "동소문동7가",
      "동소문동4가",
      "동선동2가",
      "동선동",
      "돈암제2동",
      "장위제2동",
      "동선동1가",
      "보문동7가",
      "월곡제2동",
      "삼선동",
      "동소문동2가",
      "안암동5가",
      "삼선동5가",
      "보문동3가",
      "보문동6가",
      "동소문동6가",
      "안암동",
      "돈암동",
      "안암동1가",
    ];
    var 송파구 = [
      "풍납동",
      "오륜동",
      "마천1동",
      "방이2동",
      "가락본동",
      "방이동",
      "마천동",
      "잠실4동",
      "거여1동",
      "잠실2동",
      "문정동",
      "위례동",
      "잠실본동",
      "거여2동",
      "오금동",
      "잠실3동",
      "송파2동",
      "잠실6동",
      "문정1동",
      "석촌동",
      "신천동",
      "방이1동",
      "거여동",
      "장지동",
      "잠실동",
      "가락2동",
      "가락동",
      "송파1동",
      "송파동",
      "풍납2동",
      "가락1동",
      "풍납1동",
      "삼전동",
      "문정2동",
      "마천2동",
      "잠실7동",
    ];
    var 양천구 = [
      "목1동",
      "신정동",
      "신월동",
      "신월4동",
      "신월7동",
      "신월2동",
      "신월1동",
      "신월6동",
      "신정6동",
      "목동",
      "신월3동",
      "신정7동",
      "신정4동",
      "신정2동",
      "신정3동",
      "신정1동",
      "신월5동",
      "목3동",
      "목5동",
      "목4동",
      "목2동",
    ];

    var 영등포구 = [
      "신길제5동",
      "신길제3동",
      "양평동1가",
      "여의동",
      "양평동5가",
      "양화동",
      "영등포동",
      "문래동1가",
      "양평동6가",
      "신길제4동",
      "양평동2가",
      "대림제2동",
      "당산동6가",
      "양평동3가",
      "대림제1동",
      "문래동2가",
      "양평제1동",
      "양평동4가",
      "영등포동3가",
      "당산동4가",
      "문래동4가",
      "당산제2동",
      "대림동",
      "신길동",
      "영등포동8가",
      "여의도동",
      "양평동",
      "영등포동7가",
      "신길제1동",
      "당산제1동",
      "영등포동2가",
      "도림동",
      "영등포동4가",
      "당산동3가",
      "당산동1가",
      "신길제6동",
      "영등포동1가",
      "영등포동5가",
      "양평제2동",
      "문래동3가",
      "당산동2가",
      "신길제7동",
      "대림제3동",
      "당산동5가",
      "영등포본동",
      "영등포동6가",
      "문래동6가",
      "문래동",
      "당산동",
      "문래동5가",
    ];
    var 용산구 = [
      "원효로1가",
      "용문동",
      "청파동",
      "원효로4가",
      "산천동",
      "신계동",
      "효창동",
      "도원동",
      "원효로3가",
      "용산동6가",
      "청파동2가",
      "원효로제1동",
      "한강로1가",
      "용산동2가",
      "후암동",
      "청파동1가",
      "용산동5가",
      "갈월동",
      "신창동",
      "보광동",
      "주성동",
      "용산동3가",
      "이태원제2동",
      "한강로동",
      "남영동",
      "한남동",
      "문배동",
      "용산동4가",
      "한강로2가",
      "원효로2가",
      "청암동",
      "서빙고동",
      "이촌제1동",
      "동자동",
      "이태원제1동",
      "용산동1가",
      "청파동3가",
      "이태원동",
      "용산2가동",
      "이촌제2동",
      "원효로제2동",
      "동빙고동",
      "이촌동",
      "서계동",
      "한강로3가",
    ];
    var 은평구 = [
      "진관동",
      "불광제2동",
      "신사제2동",
      "응암제3동",
      "신사동",
      "대조동",
      "갈현동",
      "불광제1동",
      "증산동",
      "신사제1동",
      "역촌동",
      "불광동",
      "구산동",
      "응암제2동",
      "녹번동",
      "갈현제1동",
      "갈현제2동",
      "응암제1동",
      "응암동",
      "수색동",
    ];
    var 종로구 = [
      "안국동",
      "무악동",
      "계동",
      "창신제2동",
      "종로6가",
      "창신제1동",
      "명륜2가",
      "종로5.6가동",
      "운니동",
      "누상동",
      "종로2가",
      "신교동",
      "명륜3가",
      "효자동",
      "원서동",
      "당주동",
      "관수동",
      "청운효자동",
      "관철동",
      "송현동",
      "충신동",
      "옥인동",
      "수송동",
      "구기동",
      "가회동",
      "부암동",
      "명륜1가",
      "견지동",
      "필운동",
      "삼청동",
      "소격동",
      "사직동",
      "인사동",
      "청진동",
      "돈의동",
      "봉익동",
      "훈정동",
      "궁정동",
      "인의동",
      "신영동",
      "경운동",
      "숭인제1동",
      "묘동",
      "종로1.2.3.4가동",
      "와룡동",
      "평동",
      "효제동",
      "통의동",
      "청운동",
      "교남동",
      "종로3가",
      "창신동",
      "적선동",
      "도렴동",
      "체부동",
      "세종로",
      "종로1가",
      "평창동",
      "신문로2가",
      "연건동",
      "화동",
      "이화동",
      "권농동",
      "신문로1가",
      "종로5가",
      "낙원동",
      "예지동",
      "관훈동",
      "교북동",
      "종로4가",
      "창성동",
      "내자동",
      "재동",
      "홍파동",
      "숭인제2동",
      "서린동",
      "통인동",
      "창신제3동",
      "명륜4가",
      "송월동",
      "연지동",
      "중학동",
      "공평동",
      "숭인동",
      "내수동",
      "홍지동",
      "동숭동",
      "장사동",
      "익선동",
      "팔판동",
      "혜화동",
      "누하동",
      "행촌동",
      "사간동",
      "원남동",
    ];
    var 중구 = [
      "을지로4가",
      "태평로1가",
      "오장동",
      "광희동2가",
      "회현동1가",
      "예장동",
      "충무로5가",
      "예관동",
      "주자동",
      "필동",
      "회현동2가",
      "봉래동1가",
      "무교동",
      "남대문로3가",
      "충무로4가",
      "회현동3가",
      "남대문로5가",
      "무학동",
      "수하동",
      "필동3가",
      "광희동1가",
      "충정로1가",
      "을지로3가",
      "장충동1가",
      "서소문동",
      "산림동",
      "필동1가",
      "신당제5동",
      "방산동",
      "충무로2가",
      "을지로동",
      "을지로2가",
      "삼각동",
      "명동2가",
      "을지로6가",
      "남산동2가",
      "다동",
      "다산동",
      "저동2가",
      "장충동",
      "남대문로2가",
      "초동",
      "인현동1가",
      "동화동",
      "약수동",
      "남산동1가",
      "남학동",
      "장충동2가",
      "을지로5가",
      "회현동",
      "태평로2가",
      "봉래동2가",
      "소공동",
      "의주로2가",
      "수표동",
      "충무로1가",
      "광희동",
      "충무로3가",
      "남대문로4가",
      "정동",
      "묵정동",
      "입정동",
      "을지로1가",
      "만리동1가",
      "필동2가",
      "남산동3가",
      "황학동",
      "주교동",
      "장교동",
      "저동1가",
      "남대문로1가",
      "명동",
      "의주로1가",
      "북창동",
      "명동1가",
      "을지로7가",
      "흥인동",
      "신당동",
      "쌍림동",
      "순화동",
      "중림동",
      "남창동",
      "인현동2가",
      "만리동2가",
      "청구동",
    ];
    var 중랑구 = [
      "면목동",
      "묵제2동",
      "상봉제2동",
      "신내동",
      "면목제2동",
      "묵동",
      "면목제7동",
      "상봉제1동",
      "면목제4동",
      "망우동",
      "중화동",
      "면목제5동",
      "중화제1동",
      "상봉동",
      "신내2동",
      "신내1동",
      "망우제3동",
      "면목본동",
      "면목제3.8동",
      "망우본동",
      "중화제2동",
      "묵제1동",
    ];
  }
  /*else if (localParent.value == "c") {
    var 남구 = ["빅토리아", "엠버", "루나", "크리스탈"];
    var 공주시 = ["LE", "하니", "정화", "혜린", "솔지"];
  }*/
  var target = document.getElementById("localChildTwo");

  if (localChildOne.value == "강남구") var localChildTwo = 강남구;
  else if (localChildOne.value == "강동구") var localChildTwo = 강동구;
  else if (localChildOne.value == "강북구") var localChildTwo = 강북구;
  else if (localChildOne.value == "강서구") var localChildTwo = 강서구;
  else if (localChildOne.value == "관악구") var localChildTwo = 관악구;
  else if (localChildOne.value == "광진구") var localChildTwo = 광진구;
  else if (localChildOne.value == "구로구") var localChildTwo = 구로구;
  else if (localChildOne.value == "금천구") var localChildTwo = 금천구;
  else if (localChildOne.value == "노원구") var localChildTwo = 노원구;
  else if (localChildOne.value == "도봉구") var localChildTwo = 도봉구;
  else if (localChildOne.value == "동대문구") var localChildTwo = 동대문구;
  else if (localChildOne.value == "동작구") var localChildTwo = 동작구;
  else if (localChildOne.value == "마포구") var localChildTwo = 마포구;
  else if (localChildOne.value == "서대문구") var localChildTwo = 서대문구;
  else if (localChildOne.value == "서초구") var localChildTwo = 서초구;
  else if (localChildOne.value == "성동구") var localChildTwo = 성동구;
  else if (localChildOne.value == "성북구") var localChildTwo = 성북구;
  else if (localChildOne.value == "송파구") var localChildTwo = 송파구;
  else if (localChildOne.value == "양천구") var localChildTwo = 양천구;
  else if (localChildOne.value == "영등포구") var localChildTwo = 영등포구;
  else if (localChildOne.value == "용산구") var localChildTwo = 용산구;
  else if (localChildOne.value == "은평구") var localChildTwo = 은평구;
  else if (localChildOne.value == "종로구") var localChildTwo = 종로구;
  else if (localChildOne.value == "중구") var localChildTwo = 중구;
  else if (localChildOne.value == "중랑구") var localChildTwo = 중랑구;

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

/*지역 selectBox url 변환*/
$(document).ready(function () {
  $("#localParent").change(function () {
    var postSido = $(this).val();

    updateURL(postSido, null, null);
  });

  $("#localChildOne").change(function () {
    var postSido = $("#localParent").val();
    var postGugun = $(this).val();
    updateURL(postSido, postGugun, null);
  });

  $("#localChildTwo").change(function () {
    var postSido = $("#localParent").val();
    var postGugun = $("#localChildOne").val();
    var postDong = $(this).val();
    updateURL(postSido, postGugun, postDong);
  });

  function updateURL(postSido, postGugun, postDong) {
    var url = "/";
    if (postSido) {
      url += "/" + postSido;
    }
    if (postGugun) {
      url += "/" + postGugun;
    }
    if (postDong) {
      url += "/" + postDong;
    }

    window.location.href = url;
  }
});
