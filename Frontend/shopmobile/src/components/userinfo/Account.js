import * as React from 'react';
import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: 'center',
  color: theme.palette.text.secondary,
}));

export default function Account() {
  return (
    <Box sx={{ flexGrow: 1 }}>
        내 정보
      <Grid container spacing={2} sx={{ mt:9 }}>
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
    </Box>
  );
}