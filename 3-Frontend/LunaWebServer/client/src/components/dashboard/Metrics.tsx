import { Card, CardContent } from "@/components/ui/card";
import { Package, PackageOpen, Truck, PackageCheck } from "lucide-react";

const metrics = [
  { 
    title: "Total Products",
    value: "285",
    icon: Package,
    description: "Product Types"
  },
  {
    title: "Total Stock",
    value: "15000",
    icon: PackageOpen,
    description: "Items in Warehouse"
  },
  {
    title: "Pending Orders",
    value: "1000",
    icon: Truck,
    description: "Orders to Process"
  },
  {
    title: "Completed Orders",
    value: "1000",
    icon: PackageCheck,
    description: "Orders Fulfilled"
  }
];

export default function Metrics() {
  return (
    <div className="grid grid-cols-1 md:grid-cols-4 gap-6">
      {metrics.map((metric) => (
        <Card key={metric.title}>
          <CardContent className="pt-6">
            <div className="flex items-center space-x-4">
              <metric.icon className="w-8 h-8 text-primary" />
              <div>
                <div className="text-2xl font-bold">{metric.value}</div>
                <div className="text-sm text-muted-foreground">{metric.description}</div>
              </div>
            </div>
          </CardContent>
        </Card>
      ))}
    </div>
  );
}
