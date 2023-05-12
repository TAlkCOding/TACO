package sideproject.talkcoding.controller.user;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import sideproject.talkcoding.file.FileStore;
import sideproject.talkcoding.file.UploadFile;
import sideproject.talkcoding.model.dto.user.UserDto;
import sideproject.talkcoding.model.entity.user.UserEntity;
import sideproject.talkcoding.service.user.UserService;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileStore fileStore;

    // 로그인 페이지 넘어가기
    @GetMapping("/login")
    public String loginPage(HttpSession session){
        Long userIndex = (Long) session.getAttribute("userIndex");
        if(userIndex != null){
            return "redirect:/";
        }
        return "login";
    }

    // 로그인 페이지에서 로그인 버튼 클릭시 실행
    // html form - post
    // return "redirect:/login";
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("userId") String userId,@RequestParam("userPassword") String userPassword, HttpSession session){
        Long userIndex = userService.login(userId, userPassword);
        if(userIndex == null){
            return new ResponseEntity<>("no userData", HttpStatus.BAD_REQUEST);
            // return "redirect:/login";
        }
        session.setAttribute("userIndex", userIndex);
        log.info(session.getAttribute("userIndex").toString());
        
        return new ResponseEntity<>(userIndex.toString(), HttpStatus.OK);
        // return "redirect:/";
    }

    // 로그아웃 기능
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session){
        session.invalidate();

        return new ResponseEntity<>("logout successfully", HttpStatus.OK);
        // return "redirect:/";
    }

    // 회원가입 페이지 넘어가기
    @GetMapping("/signup")
    public String signupPage(){
        return "signup";
    }

    // 회원가입 기능
    // html form - post (여기서는 @ModdelAttribute 생략함 -> model.addattribute 됨)
    // return 로그인 페이지
    // return "login.html";
    @PostMapping("/signup")
    public ResponseEntity<UserEntity> signup(UserDto userDto){
        UserEntity user = userService.save(userDto);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // 아이디 중복 체크 버튼
    // id 창에 들어간 데이터를 ajax로 가져옴
    // return 1 or 0
    @PostMapping("/check/id")
    public ResponseEntity<Integer> checkId(@RequestBody String userId){
        int trueOrFalse = userService.checkDuplicateId(userId);

        return new ResponseEntity<>(trueOrFalse, HttpStatus.OK);
    }

    // 닉네임 중복 체크 버튼
    // nickname 창에 들어간 데이터를 ajax로 가져옴
    // return 1(중복) or 0(가능)
    @PostMapping("/check/nick")
    public ResponseEntity<Integer> checkNick(@RequestBody String userNickName){
        int trueOrFalse = userService.checkDuplicateNickName(userNickName);

        return new ResponseEntity<>(trueOrFalse, HttpStatus.OK);
    }

    // 아이디 찾기 페이지 넘어가기
    @GetMapping("/find/id")
    public String findIdPage(){
        return "findid";
    }

    // 아이디 찾기 버튼 클릭시 기능
    // 이름 전화번호 받아와서 해당 아이디 보여주기
    // ajax로 데이터 받아와서 뿌리기
    // return "login.html";
    @PostMapping("/find/id")
    public ResponseEntity<String> findId(@RequestBody String userName, @RequestBody String userPhoneNumber){
        String userId = userService.findId(userName, userPhoneNumber);

        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    // 비밀번호 찾기 페이지 넘어가기
    @GetMapping("/find/pw")
    public String findPwPage(){
        return "findpw";
    }

    // 비밀번호 찾기 버튼 클릭 시 기능
    // 이름과 아이디 파라미터를 받아 비밀번호 보여주기
    // ajax로 데이터 받아서 뿌리기
    // return "login.html";
    @PostMapping("/find/pw")
    public ResponseEntity<Optional<UserEntity>> findPw(@RequestParam("userId") String userId,@RequestParam("userName") String userName){
        Optional<UserEntity> userPassword = userService.findPassword(userId, userName);
        
        return new ResponseEntity<>(userPassword, HttpStatus.OK);
    }

    // 회원정보 수정 페이지 넘어가기
    @GetMapping("/user/edit")
    public ResponseEntity<Optional<UserEntity>> UserEdit(HttpSession session, Model model){
        Long userIndex = (Long) session.getAttribute("userIndex");

        Optional<UserEntity> userInfo = userService.findUserInfo(userIndex);
        model.addAttribute("userInfo", userInfo);

        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    // 회원정보 수정 버튼 기능
    @PostMapping("/user/edit")
    public ResponseEntity<Optional<UserEntity>> userEdit(HttpSession session, @ModelAttribute UserDto userDto) throws IOException{
        Long userIndex = (Long) session.getAttribute("userIndex");
        
        UserEntity user = userDto.toEntity();
        Optional<UserEntity> userInfo = userService.changeUserInfo(userIndex, user);

        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    // 비밀번호 변경 기능
    @PostMapping("/change/pw")
    public ResponseEntity<Optional<UserEntity>> changePw(HttpSession session, @RequestParam("userPassword") String userPassword){
        Long userIndex = (Long) session.getAttribute("userIndex");
        Optional<UserEntity> userInfo = userService.findUserInfo(userIndex);
        userService.changePassword(userInfo, userPassword);
        
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    // 회원 탈퇴 버튼 기능
    @DeleteMapping("/user/delete")
    public ResponseEntity<String> deleteUserInfo(HttpSession session){
        Long userIndex = (Long) session.getAttribute("userIndex");
        userService.deleteUserInfo(userIndex);
        session.invalidate();
        return new ResponseEntity<String>("delete success", HttpStatus.OK);
    }
}
