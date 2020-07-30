-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工程与OSS文件匹配', '2000', '1', 'ossproject', 'ctag/ossproject/index', 1, 'C', '0', '0', 'ctag:ossproject:list', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '工程与OSS文件匹配菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工程与OSS文件匹配查询', @parentId, '1',  '#', '', 1,  'F', '0',  '0', 'ctag:ossproject:query',        '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工程与OSS文件匹配新增', @parentId, '2',  '#', '', 1,  'F', '0',  '0', 'ctag:ossproject:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工程与OSS文件匹配修改', @parentId, '3',  '#', '', 1,  'F', '0',  '0', 'ctag:ossproject:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工程与OSS文件匹配删除', @parentId, '4',  '#', '', 1,  'F', '0',  '0', 'ctag:ossproject:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工程与OSS文件匹配导出', @parentId, '5',  '#', '', 1,  'F', '0',  '0', 'ctag:ossproject:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
