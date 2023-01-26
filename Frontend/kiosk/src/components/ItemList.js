import * as React from "react";
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from '@mui/material/TableCell';
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Card from "@mui/material/Card";
import Typography from "@mui/material/Typography";
import CardActions from "@mui/material/CardActions";

const columns = [
  { id: "name", label: "품명", minWidth: 140 },
  { id: "price", label: "단가", minWidth: 50 },
  {
    id: "cnt",
    label: "수량",
    minWidth: 70,
    align: "right",
  },
  {
    id: "fullprice",
    label: "총액",
    minWidth: 70,
    align: "right",
  },
];

function createData(name, price, cnt) {
  const fullprice = price * cnt;
  return { name, price, cnt, fullprice };
}

const rows = [
  createData("폰트크기 바꿧지롱", 1500, 5),
  createData("동원참치", 4000, 1),
  createData("???", 10000, 3),
  createData("????", 5000, 4),
  createData("?????", 4000, 1),
  createData("??????", 5000, 2),
  createData("??", 12000, 5),
  createData("???", 8000, 6),
  createData("????", 12000, 2),
  createData("?????", 400, 3),
  createData("??????", 2400, 4),
  createData("???????", 500, 5),
  createData("???????", 500, 5),
  createData("???????", 500, 5),
  createData("???????", 500, 5),
  createData("???????", 500, 5),
  createData("???????", 500, 5),
  createData("???????", 500, 5),
  createData("???????", 500, 5),
  createData("???????", 500, 5),
  createData("???????", 500, 5),
  createData("???????", 500, 5),

];

const lstStyle = {
  fontSize: "24px",
}

export default function ItemList() {
  return (
    <Box
      sx={{
        marginTop: 2,
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
      }}
    >
      <Typography component="h1" variant="h4" sx={{ mb: 3 }}>
        장바구니
      </Typography>
      <Card sx={{ width: `90vw`, border: 1 }}>
        <Paper sx={{ width: "100%", overflow: "hidden" }}>
          <TableContainer sx={{ minHeight: `50vh`, maxHeight: `50vh` }}>
            <Table stickyHeader aria-label="sticky table" >
              <TableHead>
                <TableRow>
                  {columns.map((column) => (
                    <TableCell
                      key={column.id}
                      align={column.align}
                      style={lstStyle}
                    >
                      {column.label}
                    </TableCell>
                  ))}
                </TableRow>
              </TableHead>
              <TableBody>
                {rows.map((row) => {
                  return (
                    <TableRow
                      hover
                      role="checkbox"
                      tabIndex={-1}
                      key={row.code}
                    >
                      {columns.map((column) => {
                        const value = row[column.id];
                        return (
                          <TableCell key={column.id} align={column.align} style={lstStyle} >
                            {column.format && typeof value === "number"
                              ? column.format(value)
                              : value}
                          </TableCell>
                        );
                      })}
                    </TableRow>
                  );
                })}
              </TableBody>
            </Table>
          </TableContainer>
          <CardActions>
            총 결제금액
          </CardActions>
        </Paper>
      </Card>
      <button>물건 다시 찍기</button>
      <button>결제하기</button>
    </Box>
  );
}
