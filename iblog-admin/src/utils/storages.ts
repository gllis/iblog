interface StorageInterface {
  get: (key: string) => string;
  set: (key: string, value: any) => void;
  remove: (key: string) => void;
}

const Storages: StorageInterface = {} as StorageInterface;
const extend = (s: Storage): StorageInterface => {
  return {
    get(key) {
      return s.getItem(key) as string;
    },
    set(key, value) {
      s.setItem(key, value);
    },
    remove(key) {
      s.removeItem(key);
    }
  }
}

Object.assign(Storages, extend(window.localStorage));

export default Storages;