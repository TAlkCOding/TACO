package sideproject.talkcoding.controller.user;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import sideproject.talkcoding.model.dto.user.UserDto;
import sideproject.talkcoding.model.entity.user.UserEntity;
import sideproject.talkcoding.service.user.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
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
    public ResponseEntity<Long> login(@RequestParam("userId") String userId,@RequestParam("userPassword") String userPassword, HttpSession session){
        Long userIndex = userService.login(userId, userPassword);
        if(userIndex == null){
            return new ResponseEntity<>(userIndex, HttpStatus.BAD_REQUEST);
            // return "redirect:/login";
        }
        session.setAttribute("userIndex", userIndex);
        
        return new ResponseEntity<>(userIndex, HttpStatus.OK);
        // return "redirect:/";
    }

    // 로그아웃 기능
    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
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
    // return 1 or 0
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
    // ajax로 이름 전화번호 받아와서 해당 아이디 alert로 보여주기
    // 후 ajax로 success 후 페이지 이동
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

    // 이름과 아이디 파라미터를 받아 비밀번호 변경 페이지로 넘어가기
    // html form - post
    // return "changePw.html";
    @PostMapping("/find/pw")
    public ResponseEntity<Optional<UserEntity>> findPw(@RequestParam("userId") String userId,@RequestParam("userName") String userName, Model model){
        
        Optional<UserEntity> user = userService.findPassword(userId, userName);
        //model에 비밀번호 변경할 정보 담아서 보내고 
        model.addAttribute("user", user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // 비밀번호 변경 기능
    // 수정해야됨
    @PostMapping("/change/pw")
    public ResponseEntity<UserEntity> changePw(@RequestParam("userPassword") String userPassword){
        return null;
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

    // 회원 탈퇴 버튼 기능
    @DeleteMapping("/user/delete")
    public ResponseEntity<String> deleteUserInfo(HttpSession session){
        Long userIndex = (Long) session.getAttribute("userIndex");
        userService.deleteUserInfo(userIndex);
        return new ResponseEntity<String>("delete success", HttpStatus.OK);
    }
}
