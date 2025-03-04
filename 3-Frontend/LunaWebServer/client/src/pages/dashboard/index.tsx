import StockDonut from "@/components/dashboard/StockDonut";
import BestSellers from "@/components/dashboard/BestSellers";
import Metrics from "@/components/dashboard/Metrics";
import RevenueChart from "@/components/dashboard/RevenueChart";
import OrderTable from "@/components/dashboard/OrderTable";

const dashboard = () => {
  return (
    <main className="container mx-auto px-4 py-6">
      <div className="grid grid-cols-1 md:grid-cols-12 gap-6">
        <div className="md:col-span-3">
          <StockDonut />
        </div>
        <div className="md:col-span-9">
          <BestSellers />
        </div>
        <div className="md:col-span-12">
          <Metrics />
        </div>
        <div className="md:col-span-12">
          <RevenueChart />
        </div>
        <div className="md:col-span-12">
          <OrderTable />
        </div>
      </div>
    </main>
  );
}

export default dashboard;
