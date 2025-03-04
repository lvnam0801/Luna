import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { PieChart, Pie, Cell, ResponsiveContainer } from "recharts";
import { mockStockLevels } from "@/lib/mockData";

const COLORS = ['#22C55E', '#64748B', '#F59E0B', '#EF4444'];

export default function StockDonut() {
  return (
    <Card className="h-full">
      <CardHeader>
        <CardTitle>Stock Level</CardTitle>
      </CardHeader>
      <CardContent>
        <div className="relative h-[200px] w-full">
          <div className="absolute inset-0 flex items-center justify-center">
            <div className="text-center">
              <div className="text-4xl font-bold">250</div>
              <div className="text-sm text-muted-foreground">Product Types</div>
            </div>
          </div>
          <ResponsiveContainer width="100%" height="100%">
            <PieChart>
              <Pie
                data={mockStockLevels}
                cx="50%"
                cy="50%"
                innerRadius={60}
                outerRadius={80}
                paddingAngle={4}
                dataKey="value"
              >
                {mockStockLevels.map((entry, index) => (
                  <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                ))}
              </Pie>
            </PieChart>
          </ResponsiveContainer>
        </div>
        <div className="space-y-2 mt-4">
          {mockStockLevels.map((level, index) => (
            <div key={level.name} className="flex items-center">
              <div 
                className="w-3 h-3 rounded-full mr-2"
                style={{ backgroundColor: COLORS[index] }}
              />
              <span className="flex-1">{level.name}</span>
              <span className="text-muted-foreground">{level.count} products</span>
            </div>
          ))}
        </div>
      </CardContent>
    </Card>
  );
}
