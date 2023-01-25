import React, { useState } from "react";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Link from "@mui/material/Link";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import FormHelperText from "@mui/material/FormHelperText";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import styled from "styled-components";
import axios from "axios";
import { FormControl } from "@mui/material";
import HOST from "../../Host";
// import HOST from "../../Host";

const FormHelperTexts = styled(FormHelperText)`
  width: 100%;
  padding-left: 16px;
  font-weight: 700 !important;
  color: #d32f2f !important;
`;

const SignIn = () => {
  const theme = createTheme({
    palette: {
      primary: {
        main: "#0971f1",
        darker: "#053e85",
      },
      kakao: {
        main: "#FEE500",
        darker: "#053e85",
      },
      naver: {
        main: "#2DB400",
        darker: "#053e85",
        contrastText: "#fff",
      },
    },
  });
  const [userIdError, setUserIdError] = useState("");
  const [passwordState, setPasswordState] = useState("");
  const [loginError, setLoginError] = useState("");

  const onhandlePost = async (data) => {
    const { userid, password } = data;
    const postData = { userid, password };
    console.log(postData);

    // post
    const API_URI = `${HOST}/api/login`;
    await axios
      .post(API_URI ,postData)
      .then(function (response) {
        console.log(response, "성공");
        setLoginError("");
      })
      .catch(function (err) {
        console.log(err);
        setLoginError("로그인에 실패하였습니다. 다시 시도해주세요");
      });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const data = new FormData(e.currentTarget);
    const joinData = {
      userid: data.get("userid"),
      password: data.get("password"),
    };
    const { userid, password } = joinData;

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
        "비밀번호를 제대로 입력해주세요!"
      );
      flag = false;
    } else setPasswordState("");

    if (flag) {
      onhandlePost(joinData);
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
          <Avatar src="./images/logo.png" sx={{ mb: 2 }} />
          <Typography component="h1" variant="h5">
            로그인
          </Typography>
          <Box
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
                    label="비밀번호"
                    error={passwordState !== "" || false}
                  />
                </Grid>
                <FormHelperTexts>{passwordState}</FormHelperTexts>
              </Grid>
            </FormControl>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
              size="large"
            >
              로그인
            </Button>
            <FormHelperTexts>{loginError}</FormHelperTexts>
            <Grid container sx={{ mt: 2, mb: 2 }}>
              <Grid item xs>
                <Link href="findid" variant="body2">
                  비밀번호 찾기
                </Link>
              </Grid>
              <Grid item>
                <Link href="register" variant="body2">
                  {"회원가입"}
                </Link>
              </Grid>
            </Grid>
            <Button color="kakao" fullWidth variant="contained" sx={{ mt: 3 }}>
              카카오 로그인
            </Button>
            <Button
              color="naver"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              네이버 로그인
            </Button>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
};

export default SignIn;
