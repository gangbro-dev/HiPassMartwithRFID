import React, { useState } from "react";
import axios from "axios";
import {
  Avatar,
  Button,
  CssBaseline,
  TextField,
  FormControl,
  FormControlLabel,
  Checkbox,
  FormHelperText,
  Grid,
  Box,
  Typography,
  Container,
  Radio,
  RadioGroup,
  FormLabel,
  Link,
} from "@mui/material/";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import styled from "styled-components";
import HOST from "../../Host";

// mui의 css 우선순위가 높기때문에 important를 설정 - 실무하다 보면 종종 발생 우선순위 문제
const FormHelperTexts = styled(FormHelperText)`
  width: 100%;
  padding-left: 16px;
  font-weight: 700 !important;
  color: #d32f2f !important;
`;

const Boxs = styled(Box)`
  padding-bottom: 40px !important;
`;

function isBirthday(dateStr) {
  var year = Number(dateStr.substr(0, 4)); // 입력한 값의 0~4자리까지 (연)
  var month = Number(dateStr.substr(4, 2)); // 입력한 값의 4번째 자리부터 2자리 숫자 (월)
  var day = Number(dateStr.substr(6, 2)); // 입력한 값 6번째 자리부터 2자리 숫자 (일)
  var today = new Date(); // 날짜 변수 선언
  var yearNow = today.getFullYear(); // 올해 연도 가져옴

  if (dateStr.length <= 8) {
    // 연도의 경우 1900 보다 작거나 yearNow 보다 크다면 false를 반환합니다.
    if (1900 > year || year > yearNow) {
      return false;
    } else if (month < 1 || month > 12) {
      return false;
    } else if (day < 1 || day > 31) {
      return false;
    } else if (
      (month === 4 || month === 6 || month === 9 || month === 11) &&
      day === 31
    ) {
      return false;
    } else if (month === 2) {
      var isleap = year % 4 === 0 && (year % 100 !== 0 || year % 400 === 0);
      if (day > 29 || (day === 29 && !isleap)) {
        return false;
      } else {
        return true;
      } //end of if (day>29 || (day==29 && !isleap))
    } else {
      return true;
    } //end of if
  } else {
    //1.입력된 생년월일이 8자 초과할때 :  auth:false
    return false;
  }
}

const Register = () => {
  const theme = createTheme();
  const [checked, setChecked] = useState(false);
  const [userIdError, setUserIdError] = useState("");
  const [genderError, setGenderError] = useState("");
  const [phoneError, setPhoneError] = useState("");
  const [birthError, setBirthError] = useState("");
  const [passwordState, setPasswordState] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [nameError, setNameError] = useState("");
  const [registerError, setRegisterError] = useState("");

  const handleAgree = (event) => {
    setChecked(event.target.checked);
  };

  const onhandlePost = async (data) => {
    const { userid, password, name, gender, phone, birth, email, address } =
    data;
    birth.replace(/^(\d{4})(\d{2})(\d{2})$/, `$1-$2-$3`);
    const formData = new FormData();
    formData.append("userid", userid);
    formData.append("password", password);
    formData.append("name", name);
    formData.append("gender", gender);
    formData.append("phone", phone);
    formData.append("birth", birth);
    formData.append("email", email);
    formData.append("address", address);
    console.log(formData);

    // post
    const API_URI = `${HOST}/signup`;
    await axios
      .post(API_URI, formData)
      .then(function (response) {
        console.log(response, "성공");
      })
      .catch(function (err) {
        console.log(err);
        setRegisterError("회원가입에 실패하셨습니다. 다시한번 확인해주세요");
      });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const data = new FormData(e.currentTarget);
    const joinData = {
      userid: data.get("userid"),
      password: data.get("password"),
      rePassword: data.get("rePassword"),
      name: data.get("name"),
      gender: data.get("gender"),
      phone: data
        .get("phone")
        .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`),
      birth: data.get("birth"),
      email: data.get("email"),
      address: data.get("address"),
    };
    const {
      userid,
      password,
      rePassword,
      name,
      gender,
      phone,
      birth,
      // email,
      // address,
    } = joinData;

    let flag = true;
    // 아이디 입력 체크
    if (userid.length < 1) {
      setUserIdError("아이디를 입력해주세요");
      flag = false;
    } else setUserIdError("");

    // 비밀번호 유효성 체크
    const passwordRegex =
      /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
    if (!passwordRegex.test(password)) {
      setPasswordState(
        "숫자+영문자+특수문자 조합으로 8자리 이상 입력해주세요!"
      );
      flag = false;
    } else setPasswordState("");

    // 비밀번호 같은지 체크
    if (password !== rePassword) {
      setPasswordError("비밀번호가 일치하지 않습니다.");
      flag = false;
    } else setPasswordError("");

    // 이름 유효성 검사
    const nameRegex = /^[가-힣a-zA-Z]+$/;
    if (!nameRegex.test(name) || name.length < 1) {
      setNameError("올바른 이름을 입력해주세요.");
      flag = false;
    } else setNameError("");

    // 성별 유효성 검사
    if (gender === "남" || gender === "여") {
      setGenderError("");
    } else {
      setGenderError("성별을 선택해주세요");
      flag = false;
    }

    // 핸드폰 유효성 검사
    const phoneRegex = /01[016789]-[^0][0-9]{2,3}-[0-9]{3,4}/;
    if (!phoneRegex.test(phone) || phone.length < 1) {
      setPhoneError("핸드폰 번호를 확인해주세요");
      flag = false;
    } else setPhoneError("");

    // 생년월일 유효성 검사
    if (!isBirthday(birth) || birth.length < 1) {
      setBirthError("생년월일을 확인해주세요");
      flag = false;
    } else setBirthError("");

    // 회원가입 동의 체크
    if (!checked) alert("회원가입 약관에 동의해주세요.");

    console.log(flag);
    if (flag && checked) {
      onhandlePost(joinData);
    }
  };

  return (
    <ThemeProvider theme={theme}>
      <Container maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <Avatar src="./images/logo.png" sx={{ mb: 2 }} />
          <Typography component="h1" variant="h5">
            회원가입
          </Typography>
          <Boxs
            component="form"
            noValidate
            onSubmit={handleSubmit}
            sx={{ mt: 3 }}
          >
            <FormControl>
              <Grid container spacing={2}>
                <Grid item xs={12}>
                  <TextField
                    required
                    autoFocus
                    fullWidth
                    id="userid"
                    name="userid"
                    label="아이디"
                    error={userIdError !== "" || false}
                  />
                </Grid>
                <FormHelperTexts>{userIdError}</FormHelperTexts>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    type="password"
                    id="password"
                    name="password"
                    label="비밀번호 (숫자+영문자+특수문자 8자리 이상)"
                    error={passwordState !== "" || false}
                  />
                </Grid>
                <FormHelperTexts>{passwordState}</FormHelperTexts>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    type="password"
                    id="rePassword"
                    name="rePassword"
                    label="비밀번호 재입력"
                    error={passwordError !== "" || false}
                  />
                </Grid>
                <FormHelperTexts>{passwordError}</FormHelperTexts>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    id="name"
                    name="name"
                    label="이름"
                    error={nameError !== "" || false}
                  />
                </Grid>
                <FormHelperTexts>{nameError}</FormHelperTexts>
                <Grid item xs={12}>
                  <FormControl>
                    <FormLabel id="demo-row-radio-buttons-group-label" required>
                      성별
                    </FormLabel>
                    <RadioGroup
                      row
                      aria-labelledby="demo-row-radio-buttons-group-label"
                      name="gender"
                      error={genderError !== "" || false}
                    >
                      <FormControlLabel
                        value="여"
                        control={<Radio />}
                        label="Female"
                      />
                      <FormControlLabel
                        value="남"
                        control={<Radio />}
                        label="Male"
                      />
                    </RadioGroup>
                  </FormControl>
                </Grid>
                <FormHelperTexts>{genderError}</FormHelperTexts>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    inputProps={{ maxLength: 11 }}
                    id="phone"
                    label="휴대폰(-없이 11자리)"
                    name="phone"
                    autoComplete="new-phone"
                    error={phoneError !== "" || false}
                  />
                </Grid>
                <FormHelperTexts>{phoneError}</FormHelperTexts>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    inputProps={{ maxLength: 8 }}
                    id="birth"
                    label="생년월일(-없이 8자리)"
                    name="birth"
                    autoComplete="new-birth"
                    error={birthError !== "" || false}
                  />
                </Grid>
                <FormHelperTexts>{birthError}</FormHelperTexts>
                <Grid item xs={12}>
                  <TextField
                    fullWidth
                    name="email"
                    label="이메일"
                    type="email"
                    id="email"
                    autoComplete="new-email"
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    fullWidth
                    name="address"
                    label="주소"
                    type="address"
                    id="address"
                    autoComplete="new-address"
                  />
                </Grid>
                <Grid item xs={12}>
                  <FormControlLabel
                    control={
                      <Checkbox onChange={handleAgree} color="primary" />
                    }
                    label="회원가입 약관에 동의합니다."
                  />
                </Grid>
              </Grid>
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 1, mb: 2 }}
                size="large"
              >
                회원가입
              </Button>
            </FormControl>
            <FormHelperTexts>{registerError}</FormHelperTexts>
            <Grid container justifyContent="flex-end">
              <Grid item>
                <Link href="/" variant="body2">
                  로그인으로
                </Link>
              </Grid>
            </Grid>
          </Boxs>
        </Box>
      </Container>
    </ThemeProvider>
  );
};

export default Register;
