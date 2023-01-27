import * as React from "react";
import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import Grid from "@mui/material/Grid";
import { Button, Card, CardMedia, Container } from "@mui/material";

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: "center",
  color: theme.palette.text.secondary,
}));

export default function Account() {
  return (
    <Container component="main" maxWidth="xs">
      <Box
        sx={{
          flexGrow: 1,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Card
          sx={{
            mt: 2,
            border: 1,
            flexGrow: 1,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <CardMedia component="img" image="/images/logo.png" alt="discount" />
        </Card>
        <Grid container spacing={2} sx={{ mt: 1, mb: 1 }}>
          <Grid item xs={4}>
            <Item>아이디</Item>
          </Grid>
          <Grid item xs={8}>
            <Item>as****34</Item>
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
            <Item>홍*동</Item>
          </Grid>
          <Grid item xs={4}>
            <Item>휴대전화</Item>
          </Grid>
          <Grid item xs={8}>
            <Item>010-****-1111</Item>
          </Grid>
        </Grid>
        <Button>비밀번호 변경</Button>
      </Box>
    </Container>
  );
}
