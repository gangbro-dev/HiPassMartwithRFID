import React, { useState } from "react";
import axios from "axios";
import {
  Avatar,
  Button,
  CssBaseline,
  TextField,
  FormControl,
  FormHelperText,
  Grid,
  Box,
  Typography,
  Container,
  Link,
} from "@mui/material/";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import styled from "styled-components";

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

const FindId = () => {
  const theme = createTheme();
  const [nameError, setNameError] = useState("");
  const [phoneError, setPhoneError] = useState("");
  const [birthError, setBirthError] = useState("");
  const [findIdError, setFindIdError] = useState("");

  const onhandleGet = async (data) => {
    const { name, phone, birth } = data;
    birth.replace(/^(\d{4})(\d{2})(\d{2})$/, `$1-$2-$3`);
    const formData = new FormData();
    formData.append("name", name);
    formData.append("phone", phone);
    formData.append("birth", birth);
    // get
    await axios
      .get("/findid", formData)
      .then(function (response) {
        console.log(response, "성공");
      })
      .catch(function (err) {
        console.log(err);
        setFindIdError("비밀번호 찾기에 실패하셨습니다. 다시한번 확인하세요");
      });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const data = new FormData(e.currentTarget);
    const joinData = {
      name: data.get("name"),
      phone: data
        .get("phone")
        .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`),
      birth: data.get("birth"),
    };
    const { name, phone, birth } = joinData;

    let flag = true;

    // 이름 입력 체크
    const nameRegex = /^[가-힣a-zA-Z]+$/;
    if (!nameRegex.test(name) || name.length < 1) {
      setNameError("올바른 이름을 입력해주세요.");
      flag = false;
    } else setNameError("");

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

    if (flag) {
      onhandleGet(joinData);
    }
  };
  return (
    <ThemeProvider theme={theme}>
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <Box
        sx={{
          marginTop: 8,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Avatar src="./images/logo.png" variant="square" sx={{ mb: 2 }} />
        <Typography component="h1" variant="h5">
          아이디/비밀번호 찾기
        </Typography>
        <Boxs
          component="form"
          noValidate
          onSubmit={handleSubmit}
          sx={{ mt: 3 }}
        >
          <FormControl component="fieldset" variant="standard">
            <Grid container spacing={2}>
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
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
              size="large"
            >
              아이디/비밀번호 찾기
            </Button>
          </FormControl>
          <FormHelperTexts>{findIdError}</FormHelperTexts>
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

export default FindId;
