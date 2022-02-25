interface StorageInterface {
  get: (key: string) => string | any;
  set: (key: string, value: any) => void;
  remove: (key: string) => void;
}

const Storages: StorageInterface = {} as StorageInterface;
const extend = (s: Storage): StorageInterface => {
  return {
    get(key) {
      let value = s.getItem(key) as string;
      if (!value) return null;
      value = value.trim();
      if ((value.startsWith('{') && value.endsWith('}')) || (value.startsWith('[') && value.endsWith(']'))) {
        value = JSON.parse(value);
      }
      return value;
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