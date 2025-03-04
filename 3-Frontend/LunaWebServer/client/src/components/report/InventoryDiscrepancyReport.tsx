import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { Card } from "@/components/ui/card";

interface DiscrepancyItem {
  id: number;
  productName: string;
  expectedQuantity: number;
  actualQuantity: number;
  difference: number;
  location: string;
  lastChecked: string;
}

const mockDiscrepancies: DiscrepancyItem[] = [
  {
    id: 1,
    productName: "Nike Air Max 270",
    expectedQuantity: 100,
    actualQuantity: 95,
    difference: -5,
    location: "Kho HCM",
    lastChecked: "2024-02-27"
  },
  {
    id: 2,
    productName: "Adidas Ultraboost",
    expectedQuantity: 150,
    actualQuantity: 152,
    difference: 2,
    location: "Kho HN",
    lastChecked: "2024-02-27"
  }
];

export default function InventoryDiscrepancyReport() {
  return (
    <div className="space-y-6">
      <div className="grid grid-cols-3 gap-4">
        <Card className="p-4">
          <div className="text-sm text-muted-foreground">Tổng số chênh lệch</div>
          <div className="text-2xl font-bold">7 sản phẩm</div>
        </Card>
        <Card className="p-4">
          <div className="text-sm text-muted-foreground">Giá trị chênh lệch</div>
          <div className="text-2xl font-bold">2.5M VND</div>
        </Card>
        <Card className="p-4">
          <div className="text-sm text-muted-foreground">Kho có nhiều chênh lệch nhất</div>
          <div className="text-2xl font-bold">Kho HCM</div>
        </Card>
      </div>

      <Table>
        <TableHeader>
          <TableRow>
            <TableHead>Sản phẩm</TableHead>
            <TableHead>Số lượng dự kiến</TableHead>
            <TableHead>Số lượng thực tế</TableHead>
            <TableHead>Chênh lệch</TableHead>
            <TableHead>Vị trí</TableHead>
            <TableHead>Kiểm tra lần cuối</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {mockDiscrepancies.map((item) => (
            <TableRow key={item.id}>
              <TableCell className="font-medium">{item.productName}</TableCell>
              <TableCell>{item.expectedQuantity}</TableCell>
              <TableCell>{item.actualQuantity}</TableCell>
              <TableCell className={item.difference < 0 ? "text-red-500" : "text-green-500"}>
                {item.difference > 0 ? `+${item.difference}` : item.difference}
              </TableCell>
              <TableCell>{item.location}</TableCell>
              <TableCell>{item.lastChecked}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
}
