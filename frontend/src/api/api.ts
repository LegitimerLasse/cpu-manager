const BASE = "http://localhost:8081/api";

export interface CpuSummary {
  id: number;
  brand: string;
  model: string;
  socketName: string;
}

export interface CpuDetail {
  id: number;
  brand: string;
  model: string;
  socketId: number;
  socketName: string;
  clockSpeedGhz: number;
  cores: number;
  threads: number;
  tdpWatt: number | null;
  priceEur: number;
  version: number;
}

export interface SocketOption {
  id: number;
  name: string;
}

export interface CpuUpdateRequest {
  brand: string;
  model: string;
  socketId: number;
  clockSpeedGhz: number;
  cores: number;
  threads: number;
  tdpWatt: number | null;
  priceEur: number;
  version: number;
}

async function request<T>(path: string, init?: RequestInit): Promise<T> {
  const res = await fetch(`${BASE}${path}`, {
    headers: { "Content-Type": "application/json" },
    ...init,
  });
  if (!res.ok) {
    const body = await res.json().catch(() => ({}));
    throw new Error(body.error ?? `HTTP ${res.status}`);
  }
  return res.json();
}

export const api = {
  getCpus: () => request<CpuSummary[]>("/cpus"),
  getCpu: (id: number) => request<CpuDetail>(`/cpus/${id}`),
  updateCpu: (id: number, data: CpuUpdateRequest) =>
    request<CpuDetail>(`/cpus/${id}`, { method: "PUT", body: JSON.stringify(data) }),
  getSockets: () => request<SocketOption[]>("/sockets"),
};
