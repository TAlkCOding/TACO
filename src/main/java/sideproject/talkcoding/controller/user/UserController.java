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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import sideproject.talkcoding.model.dto.user.LoginDto;
import sideproject.talkcoding.model.dto.user.UserDto;
import sideproject.talkcoding.model.entity.image.ProfileEntity;
import sideproject.talkcoding.model.entity.user.UserEntity;
import sideproject.talkcoding.service.image.ProfileService;
import sideproject.talkcoding.service.post.PostService;
import sideproject.talkcoding.service.user.UserService;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private PostService postService;

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
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session){
        String userId = loginDto.getUserId();
        String userPassword = loginDto.getUserPassword();

        Long userIndex = userService.login(userId, userPassword);
        if(userIndex == null){
            return new ResponseEntity<>("no userData", HttpStatus.BAD_REQUEST);
            // return "redirect:/login";
        }
        else if(userIndex != null){
        session.setAttribute("userIndex", userIndex);
        log.info(session.getAttribute("userIndex").toString());
        }
        return new ResponseEntity<>(userIndex.toString(), HttpStatus.OK);
        // return "redirect:/";
    }

    // 로그아웃 기능
    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        log.info("successfully logout");

        return "redirect:/";
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
    public String signup(UserDto userDto){
        userService.save(userDto);
    
        return "redirect:/login";
    }

    // 아이디 중복 체크 버튼
    // id 창에 들어간 데이터를 ajax로 가져옴
    // return 1 or 0
    @PostMapping("/check/id")
    public ResponseEntity<Integer> checkId(@RequestParam String userId){
        int trueOrFalse = userService.checkDuplicateId(userId);

        return new ResponseEntity<>(trueOrFalse, HttpStatus.OK);
    }

    // 닉네임 중복 체크 버튼
    // nickname 창에 들어간 데이터를 ajax로 가져옴
    // return 1(중복) or 0(가능)
    @PostMapping("/check/nick")
    public ResponseEntity<Integer> checkNick(@RequestParam String userNickName){
        int trueOrFalse = userService.checkDuplicateNickName(userNickName);

        return new ResponseEntity<>(trueOrFalse, HttpStatus.OK);
    }

    @PostMapping("/check")
    public ResponseEntity<Integer> checkIdAndNick(@RequestParam String userId, @RequestParam String userNickName){
        int checkId = userService.checkDuplicateId(userId);
        int checkNick = userService.checkDuplicateNickName(userNickName);
        //아이디 닉네임 둘다 중복될 때
        if(checkId == 1 && checkNick == 1){
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
        }
        //아이디 중복될 때
        if(checkId == 1 && checkNick == 0){
            return new ResponseEntity<>(1, HttpStatus.BAD_REQUEST);
        }
        //닉네임 중복될 때
        if(checkId == 0 && checkNick == 1){
            return new ResponseEntity<>(2, HttpStatus.OK);

        }
        //아이디 닉네임 둘다 사용 가능할 때
        if(checkId == 0 && checkNick == 0){
            return new ResponseEntity<>(3, HttpStatus.OK);   
        }
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
        
    }

    // 아이디 찾기 페이지 넘어가기
    @GetMapping("/find/id")
    public String findIdPage(){
        return "findid";
    }

    // 아이디 찾기 버튼 클릭시 기능
    // 이름 전화번호 받아와서 해당 아이디 보여주기
    @PostMapping("/find/id")
    public String findId(@RequestParam("userName") String userName, @RequestParam("userPhoneNumber") String userPhoneNumber, Model model){
        String userId = userService.findId(userName, userPhoneNumber);
        model.addAttribute("userId", userId);
        return "foundid";
    }

    // 비밀번호 찾기 페이지 넘어가기
    @GetMapping("/find/pw")
    public String findPwPage(){
        return "findpw";
    }

    // 비밀번호 찾기 버튼 클릭 시 기능
    // 이름과 아이디 파라미터를 받아 비밀번호 보여주기
    @PostMapping("/find/pw")
    public String findPw(@RequestParam("userId") String userId,@RequestParam("userName") String userName,Model model){
        String userPassword = userService.findPassword(userId, userName);
        model.addAttribute("userPassword", userPassword);
        return "foundpw";
    }

    // 회원정보 수정 페이지 넘어가기
    @GetMapping("/user/edit")
    public String UserEdit(HttpSession session, Model model){
        Long userIndex = (Long) session.getAttribute("userIndex");
        
        // 프로필 가져오기
        if(userIndex != null){
            log.info(userIndex.toString());
            Optional<ProfileEntity> userProfile = profileService.findProfileEntity(userIndex);
        if(userProfile.isPresent()){
            model.addAttribute("storeFileName", userProfile.get().getStoreFileName());
            }
        }

        Optional<UserEntity> userInfo = userService.findUserInfo(userIndex);
        userInfo.ifPresent(o -> model.addAttribute("userInfo", o));

        return "mypage";
    }

    // 회원정보 수정 버튼 기능
    // 테스트위해 반환값 변경
    @PostMapping("/user/edit")
    public String userEdit(HttpSession session,@RequestParam("originFileName") MultipartFile originFileName, @ModelAttribute UserDto userDto) throws IOException{
        Long userIndex = (Long) session.getAttribute("userIndex");
        
        UserEntity user = userDto.toEntity();

        // 회원 정보 수정
        userService.changeUserInfo(userIndex, user);

        // 프로필 수정
        if(originFileName != null){
            profileService.saveProfile(originFileName, userIndex);
        }

        return "redirect:/";
    }

    @GetMapping("/user/account")
    public String userAccount(HttpSession session, Model model){
        Long userIndex = (Long) session.getAttribute("userIndex");

        // 프로필 가져오기
        if(userIndex != null){
            log.info(userIndex.toString());
            Optional<ProfileEntity> userProfile = profileService.findProfileEntity(userIndex);
        if(userProfile.isPresent()){
            model.addAttribute("storeFileName", userProfile.get().getStoreFileName());
            }
        }

        return "myaccount";
    }

    // 비밀번호 변경 기능
    @PostMapping("/change/pw")
    public ResponseEntity<Optional<UserEntity>> changePw(HttpSession session, @RequestParam("newPassword") String userPassword){
        Long userIndex = (Long) session.getAttribute("userIndex");
        Optional<UserEntity> userInfo = userService.findUserInfo(userIndex);
        userService.changePassword(userInfo, userPassword);
        
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    // 회원 탈퇴 버튼 기능
    @DeleteMapping("/user/delete")
    public ResponseEntity<UserEntity> deleteUserInfo(HttpSession session){
        Long userIndex = (Long) session.getAttribute("userIndex");
        userService.deleteUserInfo(userIndex);
        session.invalidate();
        log.info("탈퇴 되었습니다.");

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
