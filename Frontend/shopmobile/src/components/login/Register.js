import * as React from "react";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import FormControlLabel from "@mui/material/FormControlLabel";
import Link from "@mui/material/Link";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import Radio from "@mui/material/Radio";
import RadioGroup from "@mui/material/RadioGroup";
import FormControl from "@mui/material/FormControl";
import FormLabel from "@mui/material/FormLabel";

const theme = createTheme();

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

export default function SignUp() {
  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    if (isBirthday(data.get("birth"))) {
      console.log("O");
    } else {
      console.log("X");
    }
    console.log({
      userid: data.get("userid"),
      password: data.get("password"),
      name: data.get("name"),
      birth: data.get("birth").replace(/^(\d{4})(\d{2})(\d{2})$/, `$1-$2-$3`),
      phone: data
        .get("phone")
        .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`),
      email: data.get("email"),
      gender: data.get("gender"),
      address: data.get("address"),
    });
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
            회원가입
          </Typography>
          <Box
            component="form"
            noValidate
            onSubmit={handleSubmit}
            sx={{ mt: 3 }}
          >
            <Grid container spacing={2}>
              <Grid item xs={12} sm={6}>
                <TextField
                  autoComplete="given-name"
                  name="userid"
                  required
                  fullWidth
                  id="userid"
                  label="ID"
                  autoFocus
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  required
                  fullWidth
                  id="password"
                  label="Password"
                  name="password"
                  autoComplete="new-password"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="name"
                  label="Name"
                  name="name"
                  autoComplete="new-name"
                />
              </Grid>
              <Grid item xs={12}>
                <FormControl>
                  <FormLabel id="demo-row-radio-buttons-group-label" required>
                    Gender
                  </FormLabel>
                  <RadioGroup
                    row
                    aria-labelledby="demo-row-radio-buttons-group-label"
                    name="gender"
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
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="phone"
                  label="Phone"
                  name="phone"
                  autoComplete="new-phone"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="birth"
                  label="Birth"
                  name="birth"
                  autoComplete="new-birth"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  name="email"
                  label="Email"
                  type="email"
                  id="email"
                  autoComplete="new-email"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  name="address"
                  label="Address"
                  type="address"
                  id="address"
                  autoComplete="new-address"
                />
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              회원가입
            </Button>
            <Grid container justifyContent="flex-end">
              <Grid item>
                <Link href="/" variant="body2">
                  로그인으로
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}
