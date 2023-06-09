$(".summernote").summernote({
  width: 792,
  height: 505,
  minHeight: null, // 최소 높이
  maxHeight: null, // 최대 높이
  focus: true,
  lang: "ko-KR",
  placeholder: "내용을 입력해주세요.",
  toolbar: [
    // [groupName, [list of button]]
    ["fontname", ["fontname"]],
    ["fontsize", ["fontsize"]],
    ["style", ["bold", "italic", "underline", "strikethrough"]],
    ["color", ["forecolor", "color"]],
    ["table", ["table"]],
    ["para", ["ul", "ol", "paragraph"]],
    ["height", ["height"]],
    ["insert", ["link"]],
    ["view", ["codeview", "help"]],
  ],
  fontNames: [
    "Arial",
    "Arial Black",
    "Comic Sans MS",
    "Courier New",
    "맑은 고딕",
    "궁서",
    "굴림체",
    "굴림",
    "돋움체",
    "바탕체",
  ],
  fontSizes: [
    "8",
    "9",
    "10",
    "11",
    "12",
    "14",
    "16",
    "18",
    "20",
    "22",
    "24",
    "28",
    "30",
    "36",
    "50",
    "72",
  ],
});
$(".note-statusbar").hide();
