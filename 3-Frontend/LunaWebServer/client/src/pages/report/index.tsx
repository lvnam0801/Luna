import { useState } from "react";
import { Card } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { FileDown, FileText } from "lucide-react";
import InventoryDiscrepancyReport from "@/components/report/InventoryDiscrepancyReport";
import TransferReport from "@/components/report/TransferReport";
import PurchasingReport from "@/components/report/PurchasingReport";
import ReceivingReport from "@/components/report/ReceivingReport";

export default function Report() {
  const [activeTab, setActiveTab] = useState("inventory");

  const handleExportPDF = () => {
    // TODO: Implement PDF export functionality
    console.log("Exporting PDF...");
  };

  return (
    <main className="container mx-auto px-4 py-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold">Báo cáo</h1>
        <Button onClick={handleExportPDF} className="gap-2">
          <FileDown className="h-4 w-4" />
          Xuất PDF
        </Button>
      </div>

      <Card className="p-6">
        <Tabs value={activeTab} onValueChange={setActiveTab}>
          <TabsList className="grid grid-cols-4 gap-4 mb-6">
            <TabsTrigger value="inventory" className="gap-2">
              <FileText className="h-4 w-4" />
              Báo cáo chênh lệch tồn kho
            </TabsTrigger>
            <TabsTrigger value="transfer" className="gap-2">
              <FileText className="h-4 w-4" />
              Báo cáo vận chuyển
            </TabsTrigger>
            <TabsTrigger value="purchasing" className="gap-2">
              <FileText className="h-4 w-4" />
              Báo cáo mua hàng
            </TabsTrigger>
            <TabsTrigger value="receiving" className="gap-2">
              <FileText className="h-4 w-4" />
              Báo cáo nhận hàng
            </TabsTrigger>
          </TabsList>

          <TabsContent value="inventory">
            <InventoryDiscrepancyReport />
          </TabsContent>
          <TabsContent value="transfer">
            <TransferReport />
          </TabsContent>
          <TabsContent value="purchasing">
            <PurchasingReport />
          </TabsContent>
          <TabsContent value="receiving">
            <ReceivingReport />
          </TabsContent>
        </Tabs>
      </Card>
    </main>
  );
}
