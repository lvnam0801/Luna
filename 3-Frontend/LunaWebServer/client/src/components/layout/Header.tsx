import { Button } from "@/components/ui/button";
import { Settings, Bell, User } from "lucide-react";
import { useLocation, Link } from "wouter";

export default function Header() {
  const [location] = useLocation();

  const navItems = [
    { label: "Dashboard", path: "/" },
    { label: "Warehouse", path: "/warehouse" },
    { label: "Inventory", path: "/inventory" },
    { label: "Import", path: "/import" },
    { label: "Export", path: "/export" },
    { label: "Report", path: "/report" },
    { label: "Planning", path: "/planning" }
  ];

  return (
    <header className="border-b bg-white">
      <div className="container mx-auto px-4 h-16 flex items-center justify-between">
        <div className="flex items-center space-x-4">
          <h1 className="text-xl font-bold">Dashboard</h1>
          <nav className="hidden md:flex space-x-4">
            {navItems.map((item) => (
              <Link key={item.path} href={item.path}>
                <Button
                  variant={location === item.path ? "default" : "ghost"}
                  className={location === item.path ? "bg-primary text-primary-foreground" : ""}
                >
                  {item.label}
                </Button>
              </Link>
            ))}
          </nav>
        </div>
        <div className="flex items-center space-x-4">
          <Button variant="ghost" size="icon">
            <Bell className="h-5 w-5" />
          </Button>
          <Button variant="ghost" size="icon">
            <Settings className="h-5 w-5" />
          </Button>
          <Button variant="ghost" size="icon" className="rounded-full">
            <User className="h-5 w-5" />
          </Button>
        </div>
      </div>
    </header>
  );
}