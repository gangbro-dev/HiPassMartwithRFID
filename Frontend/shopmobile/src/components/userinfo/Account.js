import * as React from "react";
import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import Grid from "@mui/material/Grid";
import { Card, Container } from "@mui/material";
import Link from "@mui/material/Link";
import axios from "axios";
import HOST from "../../Host";
import { useState, useEffect } from "react";

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: "center",
  color: theme.palette.text.secondary,
}));

export default function Account() {
  const [userId, setUserId] = useState("");
  const [name, setName] = useState("");
  const [phone, setPhone] = useState("");
  const [gender, setGender] = useState("");
  const [email, setEmail] = useState("");
  const [birthDate, setbirthDate] = useState("");

  const API_URI = `${HOST}/user/1`;
  useEffect(() => {
    (async () => {
      const { data } = await axios.get(API_URI);

      setUserId(data.userId);
      setName(data.name);
      setPhone(data.phone);
      setGender(data.gender);
      setEmail(data.email);
      setbirthDate(data.birthDate);
    })();
  });
  return (
    <Container component="main" maxWidth="xs">
      <Box
        sx={{
          flexGrow: 1,
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Card
          sx={{
            mt: 2,
            border: 1,
            borderRadius: 2,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            fontWeight: "bold",
            fontSize: 35,
            backgroundColor: "#64b5f6",
          }}
        >
          내 정보 수정
        </Card>
        <Grid container spacing={2} sx={{ mt: 1, mb: 1 }}>
          <Grid item xs={4}>
            <Item>아이디</Item>
          </Grid>
          <Grid item xs={8}>
            <Item>{userId}</Item>
          </Grid>
          <Grid item xs={4}>
            <Item>패스워드</Item>
          </Grid>
          <Grid item xs={8}>
            <Item>●●●●●●●●●●●●</Item>
          </Grid>
          <Grid item xs={4}>
            <Item>이름</Item>
          </Grid>
          <Grid item xs={8}>
            <Item>{name}</Item>
          </Grid>
          <Grid item xs={4}>
            <Item>휴대전화</Item>
          </Grid>
          <Grid item xs={8}>
            <Item>{phone ? phone : "."}</Item>
          </Grid>
          <Grid item xs={4}>
            <Item>성별</Item>
          </Grid>
          <Grid item xs={8}>
            <Item>{gender}</Item>
          </Grid>
          <Grid item xs={4}>
            <Item>생년월일</Item>
          </Grid>
          <Grid item xs={8}>
            <Item>{birthDate}</Item>
          </Grid>
          <Grid item xs={4}>
            <Item>이메일</Item>
          </Grid>
          <Grid item xs={8}>
            <Item>{email ? email : "."}</Item>
          </Grid>
        </Grid>
        <Grid container sx={{ mt: 2, mb: 2 }}>
          <Grid item xs>
            <Link href="/app/register" variant="body2">
              개인정보인증
            </Link>
          </Grid>
          <Grid item>
            <Link href="/app/findid" variant="body2">
              비밀번호 변경
            </Link>
          </Grid>
        </Grid>
      </Box>
    </Container>
  );
}
