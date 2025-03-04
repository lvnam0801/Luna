import { Card } from "@/components/ui/card";
import { BarChart, Bar, XAxis, YAxis, ResponsiveContainer, PieChart, Pie, Cell } from "recharts";

const importData = [
  { day: "THỨ 2", value: 3 },
  { day: "THỨ 3", value: 2 },
  { day: "THỨ 4", value: 3 },
  { day: "THỨ 5", value: 1.5 },
  { day: "THỨ 6", value: 3 },
  { day: "THỨ 7", value: 2 },
  { day: "CHỦ NHẬT", value: 4 }
];

const exportData = [
  { day: "THỨ 2", value: 2.5 },
  { day: "THỨ 3", value: 2 },
  { day: "THỨ 4", value: 3.5 },
  { day: "THỨ 5", value: 1 },
  { day: "THỨ 6", value: 3 },
  { day: "THỨ 7", value: 2.5 },
  { day: "CHỦ NHẬT", value: 3.5 }
];

const productStats = [
  { name: "Category 1", value: 55 },
  { name: "Category 2", value: 30 },
  { name: "Category 3", value: 15 }
];

const COLORS = ['#0088FE', '#00C49F', '#FFBB28'];

export default function WarehouseOverview() {
  return (
    <div className="space-y-6">
      <div className="grid grid-cols-2 gap-6">
        <Card className="p-6">
          <h3 className="font-medium mb-4">Nhập hàng từ 2024-06-10 đến 2024-11-10</h3>
          <div className="h-[200px]">
            <ResponsiveContainer width="100%" height="100%">
              <BarChart data={importData}>
                <XAxis dataKey="day" />
                <YAxis />
                <Bar dataKey="value" fill="#8884d8" />
              </BarChart>
            </ResponsiveContainer>
          </div>
        </Card>

        <Card className="p-6">
          <h3 className="font-medium mb-4">Thống kê sản phẩm nhập</h3>
          <div className="h-[200px]">
            <ResponsiveContainer width="100%" height="100%">
              <PieChart>
                <Pie
                  data={productStats}
                  cx="50%"
                  cy="50%"
                  outerRadius={80}
                  fill="#8884d8"
                  dataKey="value"
                  label
                >
                  {productStats.map((entry, index) => (
                    <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                  ))}
                </Pie>
              </PieChart>
            </ResponsiveContainer>
          </div>
        </Card>
      </div>

      <div className="grid grid-cols-3 gap-6">
        <Card className="p-6">
          <h3 className="text-sm text-muted-foreground mb-2">Liên hệ</h3>
          <div className="space-y-4">
            <div>
              <div className="text-sm text-muted-foreground">Điện thoại:</div>
              <div>84 326 158 541</div>
            </div>
            <div>
              <div className="text-sm text-muted-foreground">Email:</div>
              <div>luna.cuchi@gmail.com</div>
            </div>
            <div>
              <div className="text-sm text-muted-foreground">Địa chỉ:</div>
              <div>Khu phố 1 - Thị trấn Củ Chi - Huyện Củ Chi - TP. Hồ Chí Minh</div>
            </div>
          </div>
        </Card>

        <Card className="p-6 col-span-2">
          <h3 className="text-sm text-muted-foreground mb-2">TỔNG QUAN HÀNG HÓA</h3>
          <div className="space-y-4">
            <div className="flex justify-between">
              <span>Sản phẩm trong kho</span>
              <span>11,000</span>
            </div>
            <div className="flex justify-between">
              <span>Tổng số đã xuất</span>
              <span>5000</span>
            </div>
            <div className="flex justify-between">
              <span>Tổng số đã nhập</span>
              <span>200000</span>
            </div>
          </div>
        </Card>
      </div>
    </div>
  );
}
