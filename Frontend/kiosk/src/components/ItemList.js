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
  createData("빙그레바나나맛우유", 1500, 5),
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
];

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
      <Card sx={{ width: 550, border: 1 }}>
        <Paper sx={{ width: "100%", overflow: "hidden" }}>
          <TableContainer sx={{ minHeight: 850, maxHeight: 850 }}>
            <Table stickyHeader aria-label="sticky table">
              <TableHead>
                <TableRow>
                  {columns.map((column) => (
                    <TableCell
                      key={column.id}
                      align={column.align}
                      style={{ minWidth: column.minWidth }}
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
                          <TableCell key={column.id} align={column.align}>
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
        </Paper>
      </Card>
      <Typography component="h1" variant="h4" sx={{ mt: 3 }}>
        으아아아아ㅏ아아아아
      </Typography>
    </Box>
  );
}
