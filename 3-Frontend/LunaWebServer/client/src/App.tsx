import { Switch, Route } from "wouter";
import { QueryClientProvider } from "@tanstack/react-query";
import { queryClient } from "./lib/queryClient";
import { Toaster } from "@/components/ui/toaster";
import Dashboard from "@/pages/dashboard";
import Warehouse from "@/pages/warehouse";
import Inventory from "@/pages/inventory";
import Import from "@/pages/import";
import Export from "@/pages/export";
import Report from "@/pages/report";
import Planning from "@/pages/planning";
import NotFound from "@/pages/not-found";
import Header from "@/components/layout/Header";

function Router() {
  return (
    <div className="min-h-screen bg-[#F8FAFC]">
      <Header />
      <Switch>
        <Route path="/" component={Dashboard} />
        <Route path="/warehouse" component={Warehouse} />
        <Route path="/inventory" component={Inventory} />
        <Route path="/import" component={Import} />
        <Route path="/export" component={Export} />
        <Route path="/report" component={Report} />
        <Route path="/planning" component={Planning} />
        <Route component={NotFound} />
      </Switch>
    </div>
  );
}

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <Router />
      <Toaster />
    </QueryClientProvider>
  );
}

export default App;